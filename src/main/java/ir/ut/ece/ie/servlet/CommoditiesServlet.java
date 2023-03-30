package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

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
        if (!StringUtils.isBlank(action) && action.equals("clear")) {
            req.setAttribute("commodities", commodityController.getCommodities());
        } else if (!StringUtils.isBlank(action) && action.equals("sort_by_rate")) {
            req.setAttribute("commodities", commodityController.getCommodities()
                    .stream()
                    .sorted(Comparator.comparingDouble(Commodity::getRating).reversed())
                    .toList());
        } else if (!StringUtils.isBlank(action) && action.equals("sort_by_price")) {
            req.setAttribute("commodities", commodityController.getCommodities()
                    .stream()
                    .sorted(Comparator.comparingLong(Commodity::getPrice))
                    .toList());
        } else if (!StringUtils.isBlank(searchValue) && !StringUtils.isBlank(action)) {
            switch (action) {
                case "search_by_category" ->
                        req.setAttribute("commodities", commodityController.getCommoditiesByCategory(searchValue));
                case "search_by_name" ->
                        req.setAttribute("commodities", commodityController.getCommoditiesByNameContains(searchValue));
            }
        } else {
            req.setAttribute("commodities", List.of());
        }
        req.getRequestDispatcher(Path.JSP.COMMODITIES).forward(req, resp);
    }
}
