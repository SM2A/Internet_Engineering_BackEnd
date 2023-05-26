package ir.ut.ece.ie.repository.cart;

import ir.ut.ece.ie.domain.cart.BuyItem;
import ir.ut.ece.ie.domain.cart.BuyList;

import java.util.List;
import java.util.Optional;

public interface BuyListRepository {
    BuyList saveBuyItemToList(String username, BuyItem buyItem);

    Optional<BuyList> findByUsername(String username);

    void removeBuyItem(String username, BuyItem buyItem);

    BuyList saveToPurchasedList(String username, List<BuyItem> buyItems);
    List<BuyItem> getPurchasedItems(String username);
}
