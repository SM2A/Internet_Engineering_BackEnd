package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.api.model.commodity.CommodityDTO;
import ir.ut.ece.ie.api.model.commodity.ScoreDTO;

import java.util.List;

public interface CommodityService {
    CommodityDTO addCommodity(CommodityDTO commodityDTO);

    CommodityDTO getCommodityById(Long id);

    List<CommodityDTO> getCommodities();

    List<CommodityDTO> getCommoditiesByProviderId(Integer id);

    List<CommodityDTO> getCommoditiesByCategory(String category);

    List<CommodityDTO> getCommoditiesByNameContains(String searchStr);

    List<CommodityDTO> getCommoditiesInPriceRange(Long from, Long to);

    CommodityDTO rateCommodity(ScoreDTO scoreDTO);

    List<CommodityDTO> getSuggestedCommodities(Long id);
}
