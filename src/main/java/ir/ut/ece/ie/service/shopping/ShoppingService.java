package ir.ut.ece.ie.service.shopping;

import ir.ut.ece.ie.domain.shopping.BuyList;

public interface ShoppingService {
    BuyList addToBuyList(String username, Long commodityId);

    BuyList getBuyList(String username);

    void removeFromBuyList(String username, Long commodityId);
}
