package ir.ut.ece.ie.repository.provider;

import ir.ut.ece.ie.domain.provider.Provider;

import java.util.ArrayList;
import java.util.List;

public class ProviderRepositoryImpl implements ProviderRepository {
    private List<Provider> providers = new ArrayList<>();

    @Override
    public Provider save(Provider provider) {
        providers.add(provider);
        return provider;
    }
}
