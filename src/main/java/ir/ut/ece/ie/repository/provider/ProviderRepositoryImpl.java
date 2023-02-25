package ir.ut.ece.ie.repository.provider;

import ir.ut.ece.ie.domain.provider.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProviderRepositoryImpl implements ProviderRepository {
    private final Map<Integer, Provider> providers = new HashMap<>();

    @Override
    public Provider save(Provider provider) {
        providers.put(provider.getId(), provider);
        return provider;
    }

    @Override
    public Optional<Provider> findById(Integer id) {
        return Optional.ofNullable(providers.getOrDefault(id, null));
    }
}
