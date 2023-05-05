package ir.ut.ece.ie.repository.buylist;

import ir.ut.ece.ie.domain.buylist.BuyItem;
import ir.ut.ece.ie.domain.buylist.BuyList;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BuyListRepositoryImpl implements BuyListRepository {
    private final Map<String, BuyList> usersBuyList = new HashMap<>();
    private final Map<String, BuyList> purchasedList = new HashMap<>();

    @Override
    public BuyList saveBuyItemToList(String username, BuyItem buyItem) {
        if (usersBuyList.containsKey(username))
            usersBuyList.get(username).getBuyItems().add(buyItem);
        else
            usersBuyList.put(username, new BuyList(username, new ArrayList<>(List.of(buyItem))));
        return usersBuyList.get(username);
    }

    @Override
    public Optional<BuyList> findByUsername(String username) {
        return Optional.ofNullable(usersBuyList.getOrDefault(username, null));
    }

    @Override
    public void removeBuyItem(String username, BuyItem buyItem) {
        usersBuyList.get(username).getBuyItems().remove(buyItem);
    }

    @Override
    public BuyList saveToPurchasedList(String username, List<BuyItem> buyItems) {
        if (purchasedList.containsKey(username))
            purchasedList.get(username).getBuyItems().addAll(buyItems);
        else
            purchasedList.put(username, new BuyList(username, new ArrayList<>(buyItems)));
        return purchasedList.get(username);
    }

    @Override
    public List<BuyItem> getPurchasedItems(String username) {
        return purchasedList.get(username).getBuyItems();
    }

}
