package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@WebServlet(name = "CommoditiesServlet", value = Path.Web.COMMODITIES)
public class CommoditiesServlet extends HttpServlet {
    private final CommodityController commodityController = Factory.getCommodityController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("commodities", commodityController.getCommodities());
        req.getRequestDispatcher(Path.JSP.COMMODITIES).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchValue = req.getParameter("search");
        String action = req.getParameter("action");
        if (!StringUtils.isBlank(searchValue) && !StringUtils.isBlank(action)) {
            switch (action) {
                case "search_by_category" ->
                        req.setAttribute("commodities", commodityController.getCommoditiesByCategory(searchValue));
                case "search_by_name" ->
                        req.setAttribute("commodities", commodityController.getCommoditiesByNameContains(searchValue));
            }
        }
        else if (!StringUtils.isBlank(action) && action.equals("clear")) {
            req.setAttribute("commodities", commodityController.getCommodities());
        }
        req.getRequestDispatcher(Path.JSP.COMMODITIES).forward(req, resp);
    }
}
