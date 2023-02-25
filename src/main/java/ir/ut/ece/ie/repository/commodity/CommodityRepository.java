package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.Optional;

public interface CommodityRepository {
    Commodity save(Commodity commodity);

    Optional<Commodity> findById(Long id);
}
