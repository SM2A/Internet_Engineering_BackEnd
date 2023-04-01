package ir.ut.ece.ie.service.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.List;
import java.util.Optional;

public interface BuyListService {
    Commodity addToBuyList(String username, Long commodityId);

    Optional<BuyList> getBuyList(String username);
    Long calculateBuyListPrice(List<Commodity> commodities);

    void removeFromBuyList(String username, Long commodityId);
}
