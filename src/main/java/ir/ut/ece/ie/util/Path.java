package ir.ut.ece.ie.util;

public class Path {
    public static class Web {
        public static final String COMMODITIES = "/commodities";
        public static final String COMMODITY = "/commodities/{commodity_id}";
        public static final String PROVIDER = "/providers/{provider_id}";
        public static final String USER = "/users/{user_id}";
        public static final String ADD_TO_BUYLIST = "/addToBuyList/{username}/{commodityId}";
    }

    public static class Template {
        public static final String COMMODITIES = "templates/commodities.peb";
        public static final String ONE_COMMODITY = "templates/commodity.peb";
        public static final String ONE_PROVIDER = "templates/provider.peb";
        public static final String USER = "templates/user.peb";
        public static final String SUCCESS = "templates/200.peb";
    }
}
