package ir.ut.ece.ie.service.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.Optional;

public interface BuyListService {
    Commodity addToBuyList(String username, Long commodityId);

    Optional<BuyList> getBuyList(String username);

    void removeFromBuyList(String username, Long commodityId);
}
