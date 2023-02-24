package ir.ut.ece.ie.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ir.ut.ece.ie.controller.provider.ProviderController;
import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.domain.user.User;

public class Dispatcher {
    private UserController userController;
    private ProviderController providerController;
    private Gson gson;

    public Dispatcher() {
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
        this.userController = new UserController();
        this.providerController = new ProviderController();
    }

    public Object dispatch(String request) throws Exception {
        String[] parts = request.split(" ");
        String command = parts[0];
        String data = parts[1];
        return switch (command) {
            case "addUser" -> userController.addUser(gson.fromJson(data, User.class));
            case "addProvider" -> providerController.addProvider(gson.fromJson(data, Provider.class));
            default -> throw new Exception("command not found");
        };
    }
}
