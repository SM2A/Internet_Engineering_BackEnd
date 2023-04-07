package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = Path.Web.LOGIN)
public class LoginServlet extends HttpServlet {
    private final UserController userController = Factory.getUserController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Path.JSP.LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            User user = userController.getUser(username);
            if (!user.getPassword().equals(password))
                throw new OnlineShopException("Username or Password is incorrect!");
            userController.login(user);
            resp.sendRedirect(Path.Web.HOME);
        } catch (OnlineShopException e) {
            req.setAttribute("errorMsg", e.getMessage());
            req.getRequestDispatcher(Path.JSP.ERROR).forward(req, resp);
        }
    }
}
