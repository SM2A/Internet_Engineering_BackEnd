package ir.ut.ece.ie.service.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;

public interface BuyListService {
    Commodity addToBuyList(String username, Long commodityId);

    BuyList getBuyList(String username);

    void removeFromBuyList(String username, Long commodityId);
}
