package ir.ut.ece.ie.service.provider;

import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;

public class ProviderServiceImpl implements ProviderService {
    private ProviderRepository repository;

    public ProviderServiceImpl() {
        this.repository = new ProviderRepositoryImpl();
    }

    @Override
    public Provider addProvider(Provider provider) {
        return repository.save(provider);
    }
}
