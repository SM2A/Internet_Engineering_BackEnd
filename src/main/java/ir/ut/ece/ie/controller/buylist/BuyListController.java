package ir.ut.ece.ie.controller.buylist;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.service.buylist.BuyListService;

import java.util.List;

public class BuyListController {
    private final BuyListService buyListService;

    public BuyListController(BuyListService buyListService) {
        this.buyListService = buyListService;
    }

    public Commodity addToBuyList(String username, Long commodityId) {
        return buyListService.addToBuyList(username, commodityId);
    }

    public List<Commodity> getBuyList(String username) {
        return buyListService.getBuyList(username).getCommodities();
    }

    public void removeFromBuyList(String username, Long commodityId) {
        buyListService.removeFromBuyList(username, commodityId);
    }
}
