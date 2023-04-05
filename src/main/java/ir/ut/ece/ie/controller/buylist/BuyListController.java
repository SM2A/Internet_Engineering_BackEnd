package ir.ut.ece.ie.controller.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.service.buylist.BuyListService;

import java.util.ArrayList;

public class BuyListController {
    private final BuyListService buyListService;

    public BuyListController(BuyListService buyListService) {
        this.buyListService = buyListService;
    }

    public Commodity addToBuyList(String username, Long commodityId) {
        return buyListService.addToBuyList(username, commodityId);
    }

    public BuyList getBuyList(String username) {
        return buyListService.getBuyList(username).orElse(new BuyList(username, new ArrayList<>()));
    }

    public void removeFromBuyList(String username, Long commodityId) {
        buyListService.removeFromBuyList(username, commodityId);
    }
    public void applyDiscount(String username, String code) {
        buyListService.applyDiscount(getBuyList(username), code);
    }
}
