package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.List;

public interface CommodityService {
    Commodity addCommodity(Commodity commodity);
    Commodity getCommodityById(Long id);
    List<Commodity> getCommodities();
    List<Commodity> getCommoditiesByCategory(String category);
    Commodity rateCommodity(String username, Long commodityId, Integer score);
}
