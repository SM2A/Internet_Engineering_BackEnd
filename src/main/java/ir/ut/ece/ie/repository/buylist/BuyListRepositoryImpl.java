package ir.ut.ece.ie.repository.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BuyListRepositoryImpl implements BuyListRepository {
    private final Map<String, BuyList> usersBuyList = new HashMap<>();

    @Override
    public BuyList saveCommodityToList(String username, Commodity commodity) {
        if (usersBuyList.containsKey(username))
            usersBuyList.get(username).getCommodities().add(commodity);
        else
            usersBuyList.put(username, new BuyList(username, new ArrayList<>(List.of(commodity))));
        return usersBuyList.get(username);
    }

    @Override
    public Optional<BuyList> findByUsername(String username) {
        return Optional.ofNullable(usersBuyList.getOrDefault(username, null));
    }

    @Override
    public void removeCommodity(String username, Commodity commodity) {
        usersBuyList.get(username).getCommodities().remove(commodity);
    }
}
