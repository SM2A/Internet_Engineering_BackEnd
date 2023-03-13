package ir.ut.ece.ie.javalinhandlers;

import io.javalin.http.Handler;
import ir.ut.ece.ie.controller.provider.ProviderController;
import ir.ut.ece.ie.util.Factory;

import java.util.Collections;

public class ProviderHandler {
    private static final ProviderController providerController = Factory.getProviderController();
    public static Handler getProvider = ctx -> ctx.render(
            "templates/provider.peb",
            Collections.singletonMap("provider", providerController.getProviderById(Integer.valueOf(ctx.pathParam("provider_id")))));
}
