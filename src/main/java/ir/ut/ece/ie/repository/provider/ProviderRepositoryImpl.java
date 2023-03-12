package ir.ut.ece.ie.repository.provider;

import ir.ut.ece.ie.domain.provider.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProviderRepositoryImpl implements ProviderRepository {
    private static ProviderRepositoryImpl INSTANCE = null;
    private final Map<Integer, Provider> providers = new HashMap<>();

    private ProviderRepositoryImpl() {

    }

    public static ProviderRepositoryImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProviderRepositoryImpl();
        return INSTANCE;
    }

    @Override
    public Provider save(Provider provider) {
        providers.put(provider.getId(), provider);
        return provider;
    }

    @Override
    public Iterable<Provider> saveAll(Iterable<Provider> providers) {
        providers.forEach(provider -> this.providers.put(provider.getId(), provider));
        return providers;
    }

    @Override
    public Optional<Provider> findById(Integer id) {
        return Optional.ofNullable(providers.getOrDefault(id, null));
    }
}
