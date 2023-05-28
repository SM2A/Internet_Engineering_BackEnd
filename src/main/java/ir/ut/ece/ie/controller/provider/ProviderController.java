package ir.ut.ece.ie.controller.provider;

import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.provider.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/providers")
public class ProviderController {
    private final ProviderService providerService;

    @PostMapping
    public Provider addProvider(@RequestBody Provider provider) {
        return providerService.addProvider(provider);
    }

    @GetMapping("/{id}")
    public Provider getProviderById(@PathVariable Integer id) {
        return providerService.getProvider(id).orElseThrow(() -> new OnlineShopException("Provider not found!"));
    }
}
