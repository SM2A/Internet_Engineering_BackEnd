package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CreditServlet", value = Path.Web.CREDIT)
public class CreditServlet extends HttpServlet {
    private final UserController userController = Factory.getUserController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Path.JSP.CREDIT).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (userController.getLoggedInUser() != null) {
                long credit = Long.parseLong(req.getParameter("credit"));
                userController.addCredit(userController.getLoggedInUser().getUsername(), credit);
                req.getRequestDispatcher(Path.JSP.BUYLIST).forward(req, resp);
            } else {
                req.getRequestDispatcher(Path.JSP.LOGIN).forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher(Path.JSP.ERROR).forward(req, resp);
        }
    }
}
