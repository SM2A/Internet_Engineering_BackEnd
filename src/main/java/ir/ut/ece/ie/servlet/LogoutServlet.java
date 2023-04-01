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

@WebServlet(name = "LogoutServlet", value = Path.Web.LOGOUT)
public class LogoutServlet extends HttpServlet {
    private final UserController userController = Factory.getUserController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (userController.getLoggedInUser() != null) {
            userController.logout();
            resp.sendRedirect(Path.Web.HOME);
        } else {
            req.setAttribute("errorMsg", "There is currently no logged in user");
            req.getRequestDispatcher(Path.JSP.ERROR).forward(req, resp);
        }
    }
}
