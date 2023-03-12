package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommodityRepositoryImpl implements CommodityRepository {
    private static CommodityRepositoryImpl INSTANCE = null;
    private final Map<Long, Commodity> commodities = new HashMap<>();

    private CommodityRepositoryImpl() {

    }

    public static CommodityRepositoryImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CommodityRepositoryImpl();
        return INSTANCE;
    }

    @Override
    public Commodity save(Commodity commodity) {
        commodities.put(commodity.getId(), commodity);
        return commodity;
    }

    @Override
    public Iterable<Commodity> saveAll(Iterable<Commodity> commodities) {
        commodities.forEach(commodity -> this.commodities.put(commodity.getId(), commodity));
        return commodities;
    }

    @Override
    public Optional<Commodity> findById(Long id) {
        return Optional.ofNullable(commodities.get(id));
    }

    @Override
    public Iterable<Commodity> findAll() {
        return commodities.values().stream().toList();
    }

    @Override
    public Iterable<Commodity> findAllByCategory(String category) {
        return commodities.values().stream()
                .filter(commodity -> commodity.getCategories().contains(category))
                .toList();
    }
}
