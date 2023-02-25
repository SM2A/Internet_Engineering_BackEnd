package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.HashMap;
import java.util.Map;

public class CommodityRepositoryImpl implements CommodityRepository {
    private Map<Long, Commodity> commodities = new HashMap<>();

    @Override
    public Commodity save(Commodity commodity) {
        commodities.put(commodity.getId(), commodity);
        return commodity;
    }
}
