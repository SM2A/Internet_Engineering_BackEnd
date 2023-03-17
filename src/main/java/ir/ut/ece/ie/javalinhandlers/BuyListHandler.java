package ir.ut.ece.ie.javalinhandlers;

import io.javalin.http.Handler;
import ir.ut.ece.ie.controller.buylist.BuyListController;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;

public class BuyListHandler {
    private static final BuyListController buyListController = Factory.getBuyListController();
    public static Handler addToBuyList = ctx -> {
        try {
            buyListController.addToBuyList(ctx.pathParam("username"), Long.valueOf(ctx.pathParam("commodityId")));
            ctx.render(Path.Template.SUCCESS);
        } catch (Exception e) {
            ctx.result(e.getMessage());
        }
    };
    public static Handler removeFromBuyList = ctx -> {
        try {
            buyListController.removeFromBuyList(ctx.pathParam("username"), Long.valueOf(ctx.pathParam("commodityId")));
            ctx.render(Path.Template.SUCCESS);
        } catch (Exception e) {
            ctx.result(e.getMessage());
        }
    };
}
