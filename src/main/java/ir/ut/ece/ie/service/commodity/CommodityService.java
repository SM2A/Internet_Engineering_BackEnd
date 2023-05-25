package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.api.dto.CommodityDTO;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;

import java.util.List;
import java.util.Optional;

public interface CommodityService {
    Commodity addCommodity(CommodityDTO commodityDTO);

    Optional<Commodity> getCommodityById(Long id);

    List<Commodity> getCommodities();

    List<Commodity> getCommoditiesByProviderId(Integer id);

    List<Commodity> getCommoditiesByCategory(String category);

    List<Commodity> getCommoditiesByNameContains(String searchStr);

    List<Commodity> getCommoditiesInPriceRange(Long from, Long to);

    Commodity rateCommodity(Score score);

    List<Commodity> getSuggestedCommodities(Long id);
}
