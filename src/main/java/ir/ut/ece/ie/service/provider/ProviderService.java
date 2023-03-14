package ir.ut.ece.ie.service.provider;

import ir.ut.ece.ie.domain.provider.Provider;

import java.util.Optional;

public interface ProviderService {
    Provider addProvider(Provider provider);

    Optional<Provider> getProvider(Integer id);
}
