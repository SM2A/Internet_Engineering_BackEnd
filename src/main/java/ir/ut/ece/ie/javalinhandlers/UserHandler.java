package ir.ut.ece.ie.javalinhandlers;

import io.javalin.http.Handler;
import ir.ut.ece.ie.controller.buylist.BuyListController;
import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;

import java.util.List;
import java.util.Map;

public class UserHandler {
    private static final UserController userController = Factory.getUserController();
    private static final BuyListController buyListController = Factory.getBuyListController();
    public static Handler getUser = ctx -> {
        try {
            User user = userController.getUser(ctx.pathParam("user_id"));
            List<Commodity> commodityList = buyListController.getBuyList(ctx.pathParam("user_id"));
            ctx.render(Path.Template.USER, Map.of("user", user, "commodities", commodityList));
        } catch (Exception e) {
            ctx.result(e.getMessage());
        }
    };
    public static Handler addCredit = ctx -> {
        try {
            userController.addCredit(ctx.pathParam("user_id"), Long.parseLong(ctx.pathParam("credit")));
            ctx.render(Path.Template.SUCCESS);
        } catch (Exception e) {
            ctx.result(e.getMessage());
        }
    };
}
