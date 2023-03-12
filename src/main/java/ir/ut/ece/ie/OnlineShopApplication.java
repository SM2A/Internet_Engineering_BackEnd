package ir.ut.ece.ie;

import io.javalin.Javalin;
import ir.ut.ece.ie.util.DataInitializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OnlineShopApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        DataInitializer.loadData("http://5.253.25.110:5000");
        Javalin app = Javalin.create().start(8000);
        app.get("/", ctx -> ctx.html("Hello world"));

        Map<String, Object> model = new HashMap<>();
        model.put("name", "morteza");
        app.get("/render", ctx -> ctx.render("home.pebble", model));
    }
}
