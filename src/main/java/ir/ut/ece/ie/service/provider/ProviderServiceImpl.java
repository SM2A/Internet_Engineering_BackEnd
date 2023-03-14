package ir.ut.ece.ie.service.provider;

import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.provider.ProviderRepository;

public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository repository;

    public ProviderServiceImpl(ProviderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Provider addProvider(Provider provider) {
        return repository.save(provider);
    }

    @Override
    public Provider getProvider(Integer id) {
        return repository.findById(id).orElseThrow(() -> new OnlineShopException("Provider not found!"));
    }
}
