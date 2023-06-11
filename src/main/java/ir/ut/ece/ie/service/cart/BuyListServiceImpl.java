package ir.ut.ece.ie.service.cart;

import ir.ut.ece.ie.api.model.cart.BuyItemDTO;
import ir.ut.ece.ie.api.model.cart.CartHistoryDTO;
import ir.ut.ece.ie.api.mapper.BuyItemMapper;
import ir.ut.ece.ie.api.mapper.CartHistoryMapper;
import ir.ut.ece.ie.domain.cart.BuyItem;
import ir.ut.ece.ie.domain.cart.CartHistory;
import ir.ut.ece.ie.domain.cart.Item;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.Discount;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.cart.CartHistoryRepository;
import ir.ut.ece.ie.repository.cart.cartRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.user.DiscountRepository;
import ir.ut.ece.ie.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyListServiceImpl implements BuyListService {
    private final cartRepository cartRepository;
    private final CartHistoryRepository cartHistoryRepository;
    private final UserRepository userRepository;
    private final CommodityRepository commodityRepository;
    private final DiscountRepository discountRepository;
    private final BuyItemMapper buyItemMapper;
    private final CartHistoryMapper cartHistoryMapper;

    @Override
    public BuyItemDTO addToBuyList(BuyItemDTO buyItemDTO) {
        BuyItem buyItem = buyItemMapper.toModel(buyItemDTO);
        buyItem = cartRepository.save(buyItem);
        return buyItemMapper.toDto(buyItem);
    }

    @Override
    public List<BuyItemDTO> getBuyList(String username) {
        List<BuyItem> buyList = cartRepository.findAllById_User_Username(username);
        return buyList.stream().map(buyItemMapper::toDto).toList();
    }

    @Override
    public void removeFromBuyList(String username, Long commodityId) {
        BuyItemDTO buyItemDTO = new BuyItemDTO();
        buyItemDTO.setUsername(username);
        buyItemDTO.setCommodityId(commodityId);
        cartRepository.delete(buyItemMapper.toModel(buyItemDTO));
    }

    @Override
    @Transactional
    public void applyDiscount(String username, String discountCode) {
        Discount discount = discountRepository.findById(discountCode)
                .orElseThrow(() -> new OnlineShopException("Discount not found"));
        User user = userRepository.findById(username).orElseThrow(() -> new OnlineShopException("User not found"));
        if (user.getUsedDiscounts().stream().anyMatch(d -> d.equals(discount))) {
            throw new OnlineShopException("The discount code has expired");
        }
        user.setCurrentDiscountCode(discountCode);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void pay(String username) {
        List<BuyItem> buyList = cartRepository.findAllById_User_Username(username);
        if (buyList.isEmpty()) {
            throw new OnlineShopException("Cart is empty. Nothing to pay for!");
        }
        User user = buyList.get(0).getId().getUser();
        long buyListPrice = buyList.stream().mapToLong(BuyItem::getPrice).sum();
        long finalPrice = buyListPrice;
        if (user.getCurrentDiscountCode() != null) {
            finalPrice = calculatePriceBasedOnDiscount(buyListPrice, user.getCurrentDiscountCode());
        }
        if (user.getCredit() - finalPrice < 0)
            throw new OnlineShopException("Insufficient credit");
        user.setCredit(user.getCredit() - finalPrice);
        if (user.getCurrentDiscountCode() != null) {
            user.getUsedDiscounts().add(getDiscount(user.getCurrentDiscountCode()));
            user.setCurrentDiscountCode(null);
        }
        List<Item> items = updateCommoditiesStocksAndExtractItems(buyList);
        CartHistory cartHistory = new CartHistory();
        cartHistory.setUser(user);
        cartHistory.setItems(items);
        cartHistoryRepository.save(cartHistory);
        cartRepository.deleteAll(buyList);
    }

    @Override
    public List<CartHistoryDTO> getPurchasedItems(String username) {
        List<CartHistory> cartHistories = cartHistoryRepository.findCartHistoriesByUser_Username(username);
        return cartHistories.stream().map(cartHistoryMapper::toDto).toList();
    }

    private List<Item> updateCommoditiesStocksAndExtractItems(List<BuyItem> buyList) {
        List<Item> items = new ArrayList<>();
        List<Commodity> commodities = new ArrayList<>();
        buyList.forEach(
                item -> {
                    Commodity commodity = item.getId().getCommodity();
                    commodity.setInStock(commodity.getInStock() - item.getQuantity());
                    commodities.add(commodity);
                    items.add(new Item(commodity, item.getQuantity()));
                });
        commodityRepository.saveAll(commodities);
        return items;
    }

    private Long calculatePriceBasedOnDiscount(Long buyListPrice, String code) {
        int discount = getDiscount(code).getAmount();
        return (long) (buyListPrice * (1 - discount / 100.0));
    }

    private Discount getDiscount(String code) {
        return discountRepository.findById(code)
                .orElseThrow(() -> new OnlineShopException("Discount not found"));
    }
}
