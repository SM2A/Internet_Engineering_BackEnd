package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    private CommodityRepository repository;

    public CommodityServiceImpl() {
        this.repository = new CommodityRepositoryImpl();
    }

    @Override
    public Commodity addCommodity(Commodity commodity) {
        return repository.save(commodity);
    }

    @Override
    public Commodity getCommodityById(Long id) {
        return null;
    }

    @Override
    public List<Commodity> getCommodities() {
        return null;
    }

    @Override
    public List<Commodity> getCommoditiesByCategory(String category) {
        return null;
    }

    @Override
    public Commodity rateCommodity(String username, Long commodityId, Integer score) {
        return null;
    }
}
