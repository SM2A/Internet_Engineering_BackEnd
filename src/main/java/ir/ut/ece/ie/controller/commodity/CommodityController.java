package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.commodity.CommodityService;

import java.util.List;
import java.util.Optional;

public class CommodityController {
    private final CommodityService commodityService;

    public CommodityController(CommodityService service) {
        this.commodityService = service;
    }

    public Commodity addCommodity(Commodity commodity) {
        return commodityService.addCommodity(commodity);
    }

    public Commodity getCommodityById(Long id) {
        return Optional.ofNullable(commodityService.getCommodityById(id))
                .orElseThrow(() -> new OnlineShopException("commodity not found"));
    }

    public List<Commodity> getCommodities() {
        return commodityService.getCommodities();
    }

    public List<Commodity> getCommoditiesByCategory(String category) {
        return commodityService.getCommoditiesByCategory(category);
    }

    public Commodity rateCommodity(Score score) {
        return commodityService.rateCommodity(score);
    }
}
