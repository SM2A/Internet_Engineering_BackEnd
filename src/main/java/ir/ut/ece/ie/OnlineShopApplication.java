package ir.ut.ece.ie;

import io.javalin.Javalin;
import ir.ut.ece.ie.javalinhandlers.BuyListHandler;
import ir.ut.ece.ie.javalinhandlers.CommodityHandler;
import ir.ut.ece.ie.javalinhandlers.ProviderHandler;
import ir.ut.ece.ie.javalinhandlers.UserHandler;
import ir.ut.ece.ie.util.DataInitializer;
import ir.ut.ece.ie.util.Path;

import java.io.IOException;

public class OnlineShopApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        DataInitializer.loadData("http://5.253.25.110:5000");

        Javalin app = Javalin.create().start(8000);
        app.get(Path.Web.COMMODITIES, CommodityHandler.getAllCommodities);
        app.get(Path.Web.COMMODITY, CommodityHandler.getCommodityById);
        app.get(Path.Web.COMMODITIES_IN_PRICE_RANGE, CommodityHandler.getAllCommoditiesInPriceRange);
        app.get(Path.Web.PROVIDER, ProviderHandler.getProvider);
        app.get(Path.Web.USER, UserHandler.getUser);
        app.post(Path.Web.ADD_TO_BUYLIST, BuyListHandler.addToBuyList);
        app.post(Path.Web.REMOVE_FROM_BUYLIST, BuyListHandler.removeFromBuyList);
    }
}
