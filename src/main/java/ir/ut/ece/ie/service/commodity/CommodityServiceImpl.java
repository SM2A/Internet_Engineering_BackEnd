package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.api.model.commodity.CommodityDTO;
import ir.ut.ece.ie.api.model.commodity.ScoreDTO;
import ir.ut.ece.ie.api.mapper.CommodityMapper;
import ir.ut.ece.ie.api.mapper.ScoreMapper;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommodityServiceImpl implements CommodityService {
    private final CommodityRepository commodityRepository;
    private final ScoreRepository scoreRepository;
    private final CommodityMapper commodityMapper;
    private final ScoreMapper scoreMapper;

    @Override
    public CommodityDTO addCommodity(CommodityDTO commodityDTO) {
        Commodity commodity = commodityMapper.toModel(commodityDTO);
        try {
            commodity = commodityRepository.save(commodity);
        } catch (Exception e) {
            throw new OnlineShopException(e.getMessage());
        }
        return commodityMapper.toDto(commodity);
    }

    @Override
    public CommodityDTO getCommodityById(Long id) {
        Commodity commodity = commodityRepository.findById(id)
                .orElseThrow(() -> new OnlineShopException("Commodity not found!"));
        return commodityMapper.toDto(commodity);
    }

    @Override
    public List<CommodityDTO> getCommodities() {
        return commodityRepository.findAll().stream().map(commodityMapper::toDto).toList();
    }

    @Override
    public List<CommodityDTO> getCommoditiesByProviderId(Integer id) {
        List<Commodity> commodities = (List<Commodity>) commodityRepository.findAllByProviderId(id);
        return commodities.stream().map(commodityMapper::toDto).toList();
    }

    @Override
    public List<CommodityDTO> getCommoditiesByCategory(String category) {
        List<Commodity> commodities = (List<Commodity>) commodityRepository.findAllByCategoriesContainsIgnoreCase(category);
        return commodities.stream().map(commodityMapper::toDto).toList();
    }

    @Override
    public List<CommodityDTO> getCommoditiesByNameContains(String searchStr) {
        List<Commodity> commodities = (List<Commodity>) commodityRepository.findAllByNameIsContainingIgnoreCase(searchStr);
        return commodities.stream().map(commodityMapper::toDto).toList();
    }

    @Override
    public List<CommodityDTO> getCommoditiesInPriceRange(Long from, Long to) {
        if (from > to) {
            throw new OnlineShopException("Invalid price range. From must be less than to");
        } else if (from < 0) {
            throw new OnlineShopException("Invalid price range. From and to must be positive");
        }
        List<Commodity> commodities = (List<Commodity>) commodityRepository.findAllByPriceIsBetween(from, to);
        return commodities.stream().map(commodityMapper::toDto).toList();
    }

    @Override
    public CommodityDTO rateCommodity(ScoreDTO scoreDTO) {
        if (scoreDTO.getScore() < 1 || scoreDTO.getScore() > 10)
            throw new OnlineShopException("Invalid score.It must be between 1 and 10");
        Score score = scoreMapper.toModel(scoreDTO);
        Commodity commodity = commodityRepository.findById(scoreDTO.getCommodityId()).orElseThrow();
        try {
            scoreRepository.saveAndFlush(score);
            commodity.setRating(commodityRepository.getRating(commodity.getId()));
            commodity = commodityRepository.save(commodity);
        } catch (Exception e) {
            throw e;
        }
        return commodityMapper.toDto(commodity);
    }

    @Override
    public List<CommodityDTO> getSuggestedCommodities(Long id) {
        //todo: can we write query directly to database?
        CommodityDTO baseCommodity = getCommodityById(id);
        List<CommodityDTO> commodityList = new ArrayList<>(getCommodities());
        commodityList.sort((c1, c2) -> compareScore(c1, c2, baseCommodity));
        commodityList = commodityList.stream().filter(commodity -> !commodity.getId().equals(id)).limit(5).toList();
        return commodityList;
    }

    private double calculateScore(CommodityDTO commodity, CommodityDTO base) {
        double score = commodity.getRating();
        boolean haveIntersect = false;
        for (String c : commodity.getCategories()) {
            if (base.getCategories().contains(c)) {
                haveIntersect = true;
                break;
            }
        }
        score += haveIntersect ? 11 : 0;
        return score;
    }

    private int compareScore(CommodityDTO c1, CommodityDTO c2, CommodityDTO base) {
        Double s1 = calculateScore(c1, base);
        Double s2 = calculateScore(c2, base);
        return s2.compareTo(s1);
    }
}
