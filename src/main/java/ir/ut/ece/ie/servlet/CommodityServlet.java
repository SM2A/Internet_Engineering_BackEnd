package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.buylist.BuyListController;
import ir.ut.ece.ie.controller.commodity.CommentController;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.controller.commodity.VoteController;
import ir.ut.ece.ie.domain.commodity.Comment;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CommodityServlet", value = Path.Web.COMMODITY)
public class CommodityServlet extends HttpServlet {

    private final CommodityController commodityController = Factory.getCommodityController();
    private final CommentController commentController = Factory.getCommentController();
    private final VoteController voteController = Factory.getVoteController();
    private final BuyListController buyListController = Factory.getBuyListController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getPathInfo().replace("/", ""));
        getRequestDispatcher(req, resp, "", id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getPathInfo().replace("/", ""));
        String username = Factory.getUserController().getLoggedInUser().getUsername();

        String action = req.getParameter("action");
        try {
            switch (action) {
                case "add" -> buyListController.addToBuyList(username, id);
                case "like" -> {
                    String commentId = req.getParameter("comment_id");
                    voteController.addVote(new Vote(username, Long.parseLong(commentId), Vote.Status.LIKE));
                }
                case "dislike" -> {
                    String commentId = req.getParameter("comment_id");
                    voteController.addVote(new Vote(username, Long.parseLong(commentId), Vote.Status.DISLIKE));
                }
                case "rate" -> {
                    String rate = req.getParameter("content");
                    commodityController.rateCommodity(new Score(username, id, Integer.valueOf(rate)));
                }
                case "comment" -> {
                    String content = req.getParameter("content");
                    commentController.addComment(new Comment(id, Factory.getUserController().getLoggedInUser().getEmail(), content));
                }
            }
        } catch (Exception e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher(Path.JSP.ERROR).forward(req, resp);
        }
        getRequestDispatcher(req, resp, action, id);
    }

    private void getRequestDispatcher(HttpServletRequest req, HttpServletResponse resp, String action, Long id)
            throws ServletException, IOException {
        req.setAttribute("commodity", commodityController.getCommodityById(id));
        req.setAttribute("comments", commentController.getCommentsOfCommodity(id));
        req.setAttribute("suggestedCommodities", commodityController.getSuggestedCommodities(id));
        if (action.equals("add")) {
            req.getRequestDispatcher(Path.JSP.SUCCESS).forward(req, resp);
        } else {
            req.getRequestDispatcher(Path.JSP.COMMODITY).forward(req, resp);
        }
    }
}
