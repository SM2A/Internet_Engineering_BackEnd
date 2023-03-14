package ir.ut.ece.ie.javalinhandlers;

import io.javalin.http.Handler;
import ir.ut.ece.ie.controller.commodity.CommentController;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;

import java.util.Collections;
import java.util.Map;

public class CommodityHandler {
    private static final CommodityController commodityController = Factory.getCommodityController();
    private static final CommentController commentController = Factory.getCommentController();
    public static Handler getAllCommodities = ctx ->
            ctx.render(
                    Path.Template.COMMODITIES,
                    Collections.singletonMap("commodities", commodityController.getCommodities()));

    public static Handler getCommodityById = ctx ->
            ctx.render(
                    Path.Template.ONE_COMMODITY, Map.of(
                            "commodity", commodityController.getCommodityById(Long.valueOf(ctx.pathParam("commodity_id"))),
                            "comments", commentController.getCommentsOfCommodity(Long.valueOf(ctx.pathParam("commodity_id"))))
            );
    public static Handler getAllCommoditiesInPriceRange = ctx ->
            ctx.render(Path.Template.COMMODITIES, Collections.singletonMap("commodities",
                    commodityController.getCommoditiesInPriceRange(
                            Long.valueOf(ctx.pathParam("start_price")),
                            Long.valueOf(ctx.pathParam("end_price")))));

    public static Handler getAllCommoditiesInCategory = ctx ->
            ctx.render(Path.Template.COMMODITIES, Collections.singletonMap("commodities",
                    commodityController.getCommoditiesByCategory(ctx.pathParam("category"))));
}
