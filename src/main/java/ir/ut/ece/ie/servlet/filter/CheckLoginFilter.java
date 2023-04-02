package ir.ut.ece.ie.servlet.filter;

import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.util.Factory;
import ir.ut.ece.ie.util.Path;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "CheckLoginFilter", value = {Path.Web.HOME, Path.Web.CREDIT, Path.Web.COMMODITIES, Path.Web.BUYLIST})
public class CheckLoginFilter implements Filter {
    private final UserController userController = Factory.getUserController();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (userController.getLoggedInUser() == null) {
            request.getRequestDispatcher(Path.JSP.LOGIN).forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
