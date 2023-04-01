package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.commodity.CommentController;
import ir.ut.ece.ie.controller.commodity.CommodityController;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getPathInfo().replace("/", ""));
        req.setAttribute("commodity", commodityController.getCommodityById(id));
        req.setAttribute("comments", commentController.getCommentsOfCommodity(id));
        req.setAttribute("suggestedCommodities", commodityController.getSuggestedCommodities(id));
        req.getRequestDispatcher(Path.JSP.COMMODITY).forward(req, resp);
    }
}
