package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.Optional;

public interface CommodityRepository {
    Commodity save(Commodity commodity);

    Iterable<Commodity> saveAll(Iterable<Commodity> commodities);

    Optional<Commodity> findById(Long id);

    Iterable<Commodity> findAll();

    Iterable<Commodity> findAllByProviderId(Integer id);

    Iterable<Commodity> findAllByCategory(String category);

    Iterable<Commodity> findAllByNameContains(String searchStr);

    Iterable<Commodity> findAllByPriceInRange(Long from, Long to);
}
