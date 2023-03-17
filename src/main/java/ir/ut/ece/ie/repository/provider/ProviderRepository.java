package ir.ut.ece.ie.repository.provider;

import ir.ut.ece.ie.domain.provider.Provider;

import java.util.Optional;

public interface ProviderRepository {
    Provider save(Provider provider);

    Iterable<Provider> saveAll(Iterable<Provider> providers);

    Optional<Provider> findById(Integer id);
}
