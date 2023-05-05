package ir.ut.ece.ie.repository.buylist;

import ir.ut.ece.ie.domain.buylist.BuyItem;
import ir.ut.ece.ie.domain.buylist.BuyList;

import java.util.List;
import java.util.Optional;

public interface BuyListRepository {
    BuyList saveBuyItemToList(String username, BuyItem buyItem);

    Optional<BuyList> findByUsername(String username);

    void removeBuyItem(String username, BuyItem buyItem);

    BuyList saveToPurchasedList(String username, List<BuyItem> buyItems);
    List<BuyItem> getPurchasedItems(String username);
}
