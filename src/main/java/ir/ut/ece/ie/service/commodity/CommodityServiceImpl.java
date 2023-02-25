package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepository;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    private CommodityRepository commodityRepository;
    private ProviderRepository providerRepository;

    public CommodityServiceImpl(CommodityRepository commodityRepository, ProviderRepository providerRepository) {
        this.commodityRepository = commodityRepository;
        this.providerRepository = providerRepository;
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
