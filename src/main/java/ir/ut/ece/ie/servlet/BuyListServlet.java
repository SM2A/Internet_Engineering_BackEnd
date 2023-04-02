package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.buylist.BuyListController;
import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.service.buylist.BuyListService;
import ir.ut.ece.ie.service.buylist.BuyListServiceImpl;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BuyListServlet", value = "/buyList")
public class BuyListServlet extends HttpServlet {
    private final UserController userController = Factory.getUserController();
    private final BuyListController buyListController = Factory.getBuyListController();
    private final BuyListService buyListService = new BuyListServiceImpl(Factory.getUserRepository(),
            Factory.getCommodityRepository(), Factory.getBuyListRepository());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userController.getLoggedInUser();
        List<Commodity> buyListCommodities = buyListController.getBuyList(user.getUsername());
        req.setAttribute("user", user);
        req.setAttribute("commodities", buyListCommodities);
        req.setAttribute("buyListPrice", buyListService.calculateBuyListPrice(buyListCommodities));
        req.getRequestDispatcher(Path.JSP.BUYLIST).forward(req, resp);
    }
}
