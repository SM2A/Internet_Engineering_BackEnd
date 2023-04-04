package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.commodity.CommodityService;

import java.util.List;

public class CommodityController {
    private final CommodityService commodityService;

    public CommodityController(CommodityService service) {
        this.commodityService = service;
    }

    public Commodity addCommodity(Commodity commodity) {
        return commodityService.addCommodity(commodity);
    }

    public Commodity getCommodityById(Long id) {
        return commodityService.getCommodityById(id).orElseThrow(() -> new OnlineShopException("commodity not found"));
    }

    public List<Commodity> getCommodities() {
        return commodityService.getCommodities();
    }

    public List<Commodity> getCommoditiesByProviderId(Integer id) {
        return commodityService.getCommoditiesByProviderId(id);
    }

    public List<Commodity> getCommoditiesByCategory(String category) {
        return commodityService.getCommoditiesByCategory(category);
    }

    public List<Commodity> getCommoditiesByNameContains(String searchStr) {
        return commodityService.getCommoditiesByNameContains(searchStr);
    }

    public List<Commodity> getCommoditiesInPriceRange(Long from, Long to) {
        return commodityService.getCommoditiesInPriceRange(from, to);
    }

    public Commodity rateCommodity(Score score) {
        return commodityService.rateCommodity(score);
    }

    public List<Commodity> getSuggestedCommodities(Long id) {
        return commodityService.getSuggestedCommodities(id);
    }
}
