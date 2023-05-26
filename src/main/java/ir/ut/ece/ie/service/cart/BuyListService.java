package ir.ut.ece.ie.service.cart;

import ir.ut.ece.ie.controller.cart.dto.BuyItemReq;
import ir.ut.ece.ie.domain.cart.BuyItem;
import ir.ut.ece.ie.domain.cart.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.List;
import java.util.Optional;

public interface BuyListService {
    Commodity addToBuyList(String username, BuyItemReq buyItem);

    Optional<BuyList> getBuyList(String username);

    void removeFromBuyList(String username, BuyItemReq buyItemReq);

    void applyDiscount(BuyList buyList, String code);

    void pay(BuyList buyList);

    List<BuyItem> getPurchasedItems(String username);
}
