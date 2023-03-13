package ir.ut.ece.ie;

import io.javalin.Javalin;
import ir.ut.ece.ie.controller.commodity.CommentController;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.util.DataInitializer;
import ir.ut.ece.ie.util.Factory;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class OnlineShopApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        DataInitializer.loadData("http://5.253.25.110:5000");
        CommodityController commodityController = Factory.getCommodityController();
        CommentController commentController = Factory.getCommentController();

        Javalin app = Javalin.create().start(8000);
        app.get("/", ctx -> ctx.render("templates/base.peb"));
        app.get("/commodities", ctx ->
                ctx.render(
                        "templates/commodities.peb",
                        Collections.singletonMap("commodities", commodityController.getCommodities())));
        app.get("/commodities/{commodity_id}", ctx ->
                ctx.render(
                        "templates/commodity.peb", Map.of(
                                "commodity", commodityController.getCommodityById(Long.valueOf(ctx.pathParam("commodity_id"))),
                                "comments", commentController.getCommentsOfCommodity(Long.valueOf(ctx.pathParam("commodity_id"))))
                ));
    }
}
