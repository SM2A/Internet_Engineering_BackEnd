package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.*;

public class CommodityRepositoryImpl implements CommodityRepository {
    private final Map<Long, Commodity> commodities = new HashMap<>();

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
    public Iterable<Commodity> findAllByProviderId(Integer id) {
        return commodities.values().stream()
                .filter(commodity -> Objects.equals(commodity.getProviderId(), id))
                .toList();
    }

    @Override
    public Iterable<Commodity> findAllByCategory(String category) {
        return commodities.values().stream()
                .filter(commodity -> {
                    List<String> categories = commodity.getCategories().stream().map(String::toLowerCase).toList();
                    return categories.contains(category.toLowerCase());
                }).toList();
    }

    @Override
    public Iterable<Commodity> findAllByPriceInRange(Long from, Long to) {
        return commodities.values().stream()
                .filter(commodity -> commodity.getPrice() >= from && commodity.getPrice() <= to)
                .toList();
    }
}
