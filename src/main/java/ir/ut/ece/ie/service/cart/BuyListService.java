package ir.ut.ece.ie.service.cart;

import ir.ut.ece.ie.api.model.cart.BuyItemDTO;
import ir.ut.ece.ie.api.model.cart.CartHistoryDTO;

import java.util.List;

public interface BuyListService {
    BuyItemDTO addToBuyList(BuyItemDTO buyItemDTO);

    List<BuyItemDTO> getBuyList(String username);

    void removeFromBuyList(String username, Long commodityId);

    void applyDiscount(String username, String discountCode);

    void pay(String username);

    List<CartHistoryDTO> getPurchasedItems(String username);
}
