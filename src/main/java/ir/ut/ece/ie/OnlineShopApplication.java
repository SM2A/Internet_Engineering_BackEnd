package ir.ut.ece.ie;

import io.javalin.Javalin;
import ir.ut.ece.ie.javalinhandlers.CommodityHandler;
import ir.ut.ece.ie.javalinhandlers.ProviderHandler;
import ir.ut.ece.ie.util.DataInitializer;

import java.io.IOException;

public class OnlineShopApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        DataInitializer.loadData("http://5.253.25.110:5000");

        Javalin app = Javalin.create().start(8000);
        app.get("/", ctx -> ctx.render("templates/base.peb"));
        app.get("/commodities", CommodityHandler.getAllCommodities);
        app.get("/commodities/{commodity_id}", CommodityHandler.getCommodityById);
        app.get("/providers/{provider_id}", ProviderHandler.getProvider);
    }
}
