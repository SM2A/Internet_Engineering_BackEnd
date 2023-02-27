package ir.ut.ece.ie.repository.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.Optional;

public interface BuyListRepository {
    BuyList saveCommodityToList(String username, Commodity commodity);

    Optional<BuyList> findByUsername(String username);

    void removeCommodity(String username, Commodity commodity);
}
