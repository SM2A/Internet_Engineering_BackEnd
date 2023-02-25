package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommodityRepositoryImpl implements CommodityRepository {
    private Map<Long, Commodity> commodities = new HashMap<>();

    @Override
    public Commodity save(Commodity commodity) {
        commodities.put(commodity.getId(), commodity);
        return commodity;
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
