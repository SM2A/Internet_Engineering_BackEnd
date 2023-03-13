package ir.ut.ece.ie.service.provider;

import ir.ut.ece.ie.domain.provider.Provider;

public interface ProviderService {
    Provider addProvider(Provider provider);
    Provider getProvider(Integer id);
}
