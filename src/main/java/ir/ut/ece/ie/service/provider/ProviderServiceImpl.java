package ir.ut.ece.ie.service.provider;

import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository repository;

    @Override
    public Provider addProvider(Provider provider) {
        return repository.save(provider);
    }

    @Override
    public Optional<Provider> getProvider(Integer id) {
        return repository.findById(id);
    }
}
