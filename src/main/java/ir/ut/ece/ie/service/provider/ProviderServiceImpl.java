package ir.ut.ece.ie.service.provider;

import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.repository.provider.ProviderRepository;

public class ProviderServiceImpl implements ProviderService {
    private ProviderRepository repository;

    public ProviderServiceImpl(ProviderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Provider addProvider(Provider provider) {
        return repository.save(provider);
    }
}
