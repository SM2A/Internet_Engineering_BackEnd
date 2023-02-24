package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

public interface CommodityRepository {
    Commodity save(Commodity commodity);
}
