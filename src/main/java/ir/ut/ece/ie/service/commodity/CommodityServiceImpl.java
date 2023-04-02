package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.ScoreRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.user.UserRepository;

import java.util.*;

public class CommodityServiceImpl implements CommodityService {
    private final CommodityRepository commodityRepository;
    private final ScoreRepository scoreRepository;
    private final ProviderRepository providerRepository;
    private final UserRepository userRepository;

    public CommodityServiceImpl(CommodityRepository commodityRepository, ScoreRepository scoreRepository,
                                ProviderRepository providerRepository, UserRepository userRepository) {
        this.commodityRepository = commodityRepository;
        this.scoreRepository = scoreRepository;
        this.providerRepository = providerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Commodity addCommodity(Commodity commodity) {
        providerRepository.findById(commodity.getProviderId()).orElseThrow(() -> new OnlineShopException("provider not found"));
        return commodityRepository.save(commodity);
    }

    @Override
    public Optional<Commodity> getCommodityById(Long id) {
        return commodityRepository.findById(id);
    }

    @Override
    public List<Commodity> getCommodities() {
        return (List<Commodity>) commodityRepository.findAll();
    }

    @Override
    public List<Commodity> getCommoditiesByProviderId(Integer id) {
        return (List<Commodity>) commodityRepository.findAllByProviderId(id);
    }

    @Override
    public List<Commodity> getCommoditiesByCategory(String category) {
        return (List<Commodity>) commodityRepository.findAllByCategory(category);
    }

    @Override
    public List<Commodity> getCommoditiesByNameContains(String searchStr) {
        return (List<Commodity>) commodityRepository.findAllByNameContains(searchStr);
    }

    @Override
    public List<Commodity> getCommoditiesInPriceRange(Long from, Long to) {
        if (from > to) throw new OnlineShopException("Invalid price range. From must be less than to");
        if (from < 0) throw new OnlineShopException("Invalid price range. From and to must be positive");
        return (List<Commodity>) commodityRepository.findAllByPriceInRange(from, to);
    }

    @Override
    public Commodity rateCommodity(Score score) {
        if (score.getScore() < 1 || score.getScore() > 10)
            throw new OnlineShopException("Invalid score.It must be between 1 and 10");
        Commodity commodity = getCommodityById(score.getCommodityId())
                .orElseThrow(() -> new OnlineShopException("Commodity not found"));
        userRepository.findById(score.getUsername()).orElseThrow(() -> new OnlineShopException("User not found"));
        scoreRepository.save(score);
        List<Score> scoreList = (List<Score>) scoreRepository.findAllByCommodityId(score.getCommodityId());
        int numOfRatings = scoreList.size();
        double sumOfScores = scoreList.stream().mapToInt(Score::getScore).sum();
        commodity.setRating(sumOfScores / numOfRatings);
        commodityRepository.save(commodity);
        return commodity;
    }

    public double scoreCalc(Commodity commodity, Commodity base) {
        double score = commodity.getRating();
        boolean haveIntersect = false;
        for (String c: commodity.getCategories()) {
            if (base.getCategories().contains(c)) {
                haveIntersect = true;
                break;
            }
        }
        score += haveIntersect ? 11 : 0;
        return score;
    }

    public int compareScore(Commodity c1, Commodity c2, Commodity base) {
        Double s1 = scoreCalc(c1, base);
        Double s2 = scoreCalc(c2, base);
        return s2.compareTo(s1);
    }

    @Override
    public List<Commodity> getSuggestedCommodities(Long id) {
        Commodity baseCommodity = getCommodityById(id).get();
        List<Commodity> commodityList = new ArrayList<>(getCommodities());
        commodityList.sort((c1, c2) -> compareScore(c1, c2, baseCommodity));
        commodityList = commodityList.stream().filter(commodity -> !commodity.getId().equals(id)).limit(5).toList();
        return commodityList;
    }
}
