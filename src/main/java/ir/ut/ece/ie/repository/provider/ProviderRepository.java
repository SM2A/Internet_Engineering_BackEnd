package ir.ut.ece.ie.repository.provider;

import ir.ut.ece.ie.domain.provider.Provider;

import java.util.Optional;

public interface ProviderRepository {
    Provider save(Provider provider);

    Optional<Provider> findById(Integer id);
}
