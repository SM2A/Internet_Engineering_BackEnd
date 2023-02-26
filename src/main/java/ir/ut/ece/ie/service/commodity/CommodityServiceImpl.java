package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.ScoreRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.user.UserRepository;

import java.util.List;

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
    public Commodity getCommodityById(Long id) {
        return commodityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commodity> getCommodities() {
        return (List<Commodity>) commodityRepository.findAll();
    }

    @Override
    public List<Commodity> getCommoditiesByCategory(String category) {
        return (List<Commodity>) commodityRepository.findAllByCategory(category);
    }

    @Override
    public Commodity rateCommodity(Score score) {
        if (score.getScore() < 1 || score.getScore() > 10)
            throw new OnlineShopException("Invalid score.It must be between 1 and 10");
        if (commodityRepository.findById(score.getCommodityId()).isEmpty())
            throw new OnlineShopException("Commodity not found");
        if (userRepository.findById(score.getUsername()).isEmpty())
            throw new OnlineShopException("User not found");

        int numOfRatings = ((List<Score>) scoreRepository.findAllByCommodityId(score.getCommodityId())).size();
        Score newScore = scoreRepository.save(score);
        Commodity commodity = getCommodityById(score.getCommodityId());
        commodity.setRating((commodity.getRating() * numOfRatings + newScore.getScore()) / (numOfRatings + 1));
        commodityRepository.save(commodity);
        return commodity;
    }
}
