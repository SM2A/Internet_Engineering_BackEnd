package ir.ut.ece.ie.javalinhandlers;

import io.javalin.http.Handler;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.controller.provider.ProviderController;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;

import java.util.Map;

public class ProviderHandler {
    private static final ProviderController providerController = Factory.getProviderController();
    private static final CommodityController commodityController = Factory.getCommodityController();
    public static Handler getProvider = ctx -> {
        Provider provider = providerController.getProviderById(Integer.valueOf(ctx.pathParam("provider_id")));
        ctx.render(
                Path.Template.ONE_PROVIDER,
                Map.of("provider", provider,
                        "commodities", commodityController.getCommoditiesByProviderId(provider.getId())));
    };
}
