package ir.ut.ece.ie.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.controller.provider.ProviderController;
import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.ScoreRepository;
import ir.ut.ece.ie.repository.commodity.ScoreRepositoryImpl;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import ir.ut.ece.ie.service.commodity.CommodityServiceImpl;
import ir.ut.ece.ie.service.provider.ProviderServiceImpl;
import ir.ut.ece.ie.service.user.UserServiceImpl;

public class Dispatcher {
    private UserController userController;
    private ProviderController providerController;
    private CommodityController commodityController;
    private Gson gson;

    public Dispatcher() {
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
        UserRepository userRepository = new UserRepositoryImpl();
        ProviderRepository providerRepository = new ProviderRepositoryImpl();
        CommodityRepository commodityRepository = new CommodityRepositoryImpl();
        ScoreRepository scoreRepository = new ScoreRepositoryImpl();
        this.userController = new UserController(new UserServiceImpl(userRepository));
        this.providerController = new ProviderController(new ProviderServiceImpl(providerRepository));
        this.commodityController = new CommodityController(new CommodityServiceImpl(commodityRepository,
                scoreRepository, providerRepository, userRepository));
    }

    public Object dispatch(String request) {
        String[] parts = request.split(" ");
        String command = parts[0];
        String data = parts.length == 2 ? parts[1] : null;
        return switch (command) {
            case "addUser" -> userController.addUser(gson.fromJson(data, User.class));
            case "addProvider" -> providerController.addProvider(gson.fromJson(data, Provider.class));
            case "addCommodity" -> commodityController.addCommodity(gson.fromJson(data, Commodity.class));
            case "getCommodities" -> commodityController.getCommodities();
            case "rateCommodity" -> commodityController.rateCommodity(gson.fromJson(data, Score.class));
            case "getCommodityById" -> commodityController
                    .getCommodityById(gson.fromJson(data, JsonObject.class).get("id").getAsLong());
            case "getCommoditiesByCategory" -> commodityController
                    .getCommoditiesByCategory(gson.fromJson(data, JsonObject.class).get("category").getAsString());
            default -> throw new OnlineShopException("command not found");
        };
    }
}
