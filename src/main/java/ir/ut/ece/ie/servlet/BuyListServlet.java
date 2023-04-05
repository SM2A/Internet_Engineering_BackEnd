package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.buylist.BuyListController;
import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "BuyListServlet", value = "/buyList")
public class BuyListServlet extends HttpServlet {
    private final UserController userController = Factory.getUserController();
    private final BuyListController buyListController = Factory.getBuyListController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userController.getLoggedInUser();
        BuyList buyList = buyListController.getBuyList(user.getUsername());
        req.setAttribute("user", user);
        req.setAttribute("commodities", buyList.getCommodities());
        req.setAttribute("buyListPrice", buyList.getPrice());
        req.getRequestDispatcher(Path.JSP.BUYLIST).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userController.getLoggedInUser();
        String action = req.getParameter("action");
        try {
            switch (action) {
                case "remove" -> {
                    long commodityId = Long.parseLong(req.getParameter("commodityId"));
                    buyListController.removeFromBuyList(user.getUsername(), commodityId);
                }
            }
        } catch (Exception e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher(Path.JSP.ERROR).forward(req, resp);
        }
        doGet(req, resp);
    }
}
