package ir.ut.ece.ie.service.cart;

import ir.ut.ece.ie.api.dto.BuyItemDTO;
import ir.ut.ece.ie.api.mapper.BuyItemMapper;
import ir.ut.ece.ie.domain.cart.BuyItem;
import ir.ut.ece.ie.domain.user.Discount;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.cart.cartRepository;
import ir.ut.ece.ie.repository.user.DiscountRepository;
import ir.ut.ece.ie.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyListServiceImpl implements BuyListService {
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final cartRepository cartRepository;
    private final BuyItemMapper buyItemMapper;

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
//
//    @Override
//    public void pay(BuyList buyList) {
//        User user = userRepository.findById(buyList.getUsername()).orElseThrow(() -> new OnlineShopException("User not found"));
//        long price = buyList.getPrice();
//        if (user.getCredit() - price < 0)
//            throw new OnlineShopException("Insufficient credit");
//        user.setCredit(user.getCredit() - price);
//        user.getUsedDiscounts().add(buyList.getDiscount());
//        buyList.setDiscount(null);
//        buyList.getBuyItems().forEach(
//                item -> item.getCommodity().setInStock(item.getCommodity().getInStock() - item.getQuantity()));
//        buyListRepository.saveToPurchasedList(buyList.getUsername(), buyList.getBuyItems());
//        buyList.getBuyItems().clear();
//    }
//
//    @Override
//    public List<BuyItem> getPurchasedItems(String username) {
//        return buyListRepository.getPurchasedItems(username);
//    }
//
//
//    private boolean isAlreadyExistInBuyList(String username, Commodity commodity) {
//        Optional<BuyList> userBuyList = buyListRepository.findByUsername(username);
//        if (userBuyList.isEmpty())
//            return false;
//        for (BuyItem buyItem : userBuyList.get().getBuyItems()) {
//            if (buyItem.getCommodity().equals(commodity))
//                return true;
//        }
//        return false;
//    }
}
