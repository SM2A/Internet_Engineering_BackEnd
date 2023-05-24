package ir.ut.ece.ie.service.buylist;

import ir.ut.ece.ie.controller.buylist.dto.BuyItemReq;
import ir.ut.ece.ie.domain.buylist.BuyItem;
import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.user.Discount;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.buylist.BuyListRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.user.DiscountRepository;
import ir.ut.ece.ie.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuyListServiceImpl implements BuyListService {
    private final UserRepository userRepository;
    private final CommodityRepository commodityRepository;
    private final BuyListRepository buyListRepository;
    private final DiscountRepository discountRepository;

    @Override
    public Commodity addToBuyList(String username, BuyItemReq buyItemreq) {
        userRepository.findById(username).orElseThrow(() -> new OnlineShopException("User not found"));
        Commodity commodity = commodityRepository.findById(buyItemreq.getCommodityId())
                .orElseThrow(() -> new OnlineShopException("Commodity doesn't exist"));
        if (commodity.getInStock() <= buyItemreq.getQuantity())
            throw new OnlineShopException("Not enough commodity in stock");

        if (isAlreadyExistInBuyList(username, commodity))
            throw new OnlineShopException("Commodity already exist in buy list");
        buyListRepository.saveBuyItemToList(username, new BuyItem(commodity, buyItemreq.getQuantity()));
        return commodity;
    }

    @Override
    public Optional<BuyList> getBuyList(String username) {
        return buyListRepository.findByUsername(username);
    }

    @Override
    public void removeFromBuyList(String username, BuyItemReq buyItemReq) {
        userRepository.findById(username).orElseThrow(() -> new OnlineShopException("User not found"));
        Commodity commodity = commodityRepository.findById(buyItemReq.getCommodityId())
                .orElseThrow(() -> new OnlineShopException("Commodity not found"));
        buyListRepository.removeBuyItem(username, new BuyItem(commodity, buyItemReq.getQuantity()));
    }

    @Override
    public void applyDiscount(BuyList buyList, String code) {
        User user = userRepository.findById(buyList.getUsername()).orElseThrow(() -> new OnlineShopException("User not found"));
        Discount discount = discountRepository.findByCode(code)
                .orElseThrow(() -> new OnlineShopException("Discount not found"));
        if (user.getUsedDiscounts().contains(discount))
            throw new OnlineShopException("The discount code has expired");
        buyList.setDiscount(discount);
    }

    @Override
    public void pay(BuyList buyList) {
        User user = userRepository.findById(buyList.getUsername()).orElseThrow(() -> new OnlineShopException("User not found"));
        long price = buyList.getPrice();
        if (user.getCredit() - price < 0)
            throw new OnlineShopException("Insufficient credit");
        user.setCredit(user.getCredit() - price);
        user.getUsedDiscounts().add(buyList.getDiscount());
        buyList.setDiscount(null);
        buyList.getBuyItems().forEach(
                item -> item.getCommodity().setInStock(item.getCommodity().getInStock() - item.getQuantity()));
        buyListRepository.saveToPurchasedList(buyList.getUsername(), buyList.getBuyItems());
        buyList.getBuyItems().clear();
    }

    @Override
    public List<BuyItem> getPurchasedItems(String username) {
        return buyListRepository.getPurchasedItems(username);
    }


    private boolean isAlreadyExistInBuyList(String username, Commodity commodity) {
        Optional<BuyList> userBuyList = buyListRepository.findByUsername(username);
        if (userBuyList.isEmpty())
            return false;
        for (BuyItem buyItem : userBuyList.get().getBuyItems()) {
            if (buyItem.getCommodity().equals(commodity))
                return true;
        }
        return false;
    }
}
