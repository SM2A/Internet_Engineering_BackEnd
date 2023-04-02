package ir.ut.ece.ie.util;

public class Path {
    public static class Web {
        public static final String HOME = "/";
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
        public static final String COMMODITIES = "/commodities";
        public static final String COMMODITY = "/commodities/*";
        public static final String CREDIT = "/credit";
        public static final String BUYLIST = "/buyList";
    }

    public static class JSP {
        public static final String HOME = "/jsp/home.jsp";
        public static final String LOGIN = "/jsp/login.jsp";
        public static final String ERROR = "/jsp/error.jsp";
        public static final String COMMODITIES = "/jsp/commodities.jsp";
        public static final String COMMODITY = "/jsp/commodity.jsp";
        public static final String CREDIT = "/jsp/credit.jsp";
        public static final String SUCCESS = "/jsp/200.jsp";
        public static final String BUYLIST = "/jsp/buyList.jsp";
    }
}
