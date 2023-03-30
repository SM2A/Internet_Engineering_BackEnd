package ir.ut.ece.ie.servlet;

import ir.ut.ece.ie.util.DataInitializer;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HomeServlet", value = Path.Web.HOME)
public class HomeServlet extends HttpServlet {
    public void init() {
        try {
            DataInitializer.loadData("http://5.253.25.110:5000");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Factory.getUserController().getLoggedInUser() == null)
            resp.sendRedirect(Path.Web.LOGIN);
        else
            req.getRequestDispatcher(Path.JSP.HOME).forward(req, resp);
    }
}
