package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;

import java.util.ArrayList;
import java.util.List;

public class CommodityRepositoryImpl implements CommodityRepository {
    private List<Commodity> commodities = new ArrayList<>();

    @Override
    public Commodity save(Commodity commodity) {
        commodities.add(commodity);
        return commodity;
    }
}
