package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;

import java.util.List;

public interface CommodityService {
    Commodity addCommodity(Commodity commodity);

    Commodity getCommodityById(Long id);

    List<Commodity> getCommodities();
    List<Commodity> getCommoditiesByProviderId(Integer id);
    List<Commodity> getCommoditiesByCategory(String category);

    Commodity rateCommodity(Score score);
}
