package ir.ut.ece.ie.javalinhandlers;

import io.javalin.http.Handler;
import ir.ut.ece.ie.controller.commodity.CommentController;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.controller.commodity.VoteController;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;

import java.util.Collections;
import java.util.Map;

public class CommodityHandler {
    private static final CommodityController commodityController = Factory.getCommodityController();
    private static final CommentController commentController = Factory.getCommentController();
    private static final VoteController voteController = Factory.getVoteController();

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

    public static Handler rateCommodity = ctx -> {
        try {
            String username = ctx.pathParam("username");
            String commodityId = ctx.pathParam("commodityId");
            String rate = ctx.pathParam("rate");
            commodityController.rateCommodity(new Score(username, Long.valueOf(commodityId), Integer.valueOf(rate)));
            ctx.render(Path.Template.SUCCESS);
        } catch (Exception e) {
            ctx.result(e.getMessage());
        }
    };

    public static Handler voteComment = ctx -> {
        try {
            String username = ctx.pathParam("username");
            String commodityId = ctx.pathParam("commodityId");
            int vote = Integer.parseInt(ctx.pathParam("vote"));
            User user = Factory.getUserController().getUser(username);
            if (user == null)
                throw new Exception("User not found");
            voteController.addVote(new Vote(username, Long.parseLong(commodityId), vote));
            ctx.render(Path.Template.SUCCESS);
        } catch (Exception e) {
            ctx.result(e.getMessage());
        }
    };
}
