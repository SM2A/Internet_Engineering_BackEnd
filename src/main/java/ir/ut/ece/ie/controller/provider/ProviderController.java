package ir.ut.ece.ie.controller.provider;

import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.service.provider.ProviderService;

public class ProviderController {
    private ProviderService providerService;

    public ProviderController(ProviderService service) {
        this.providerService = service;
    }

    public Provider addProvider(Provider provider) {
        return providerService.addProvider(provider);
    }
}
