package ir.ut.ece.ie.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ir.ut.ece.ie.controller.buylist.BuyListController;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.controller.provider.ProviderController;
import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.domain.provider.Provider;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.buylist.BuyListRepository;
import ir.ut.ece.ie.repository.buylist.BuyListRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.CommodityRepository;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.ScoreRepository;
import ir.ut.ece.ie.repository.commodity.ScoreRepositoryImpl;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import ir.ut.ece.ie.service.buylist.BuyListServiceImpl;
import ir.ut.ece.ie.service.commodity.CommodityServiceImpl;
import ir.ut.ece.ie.service.provider.ProviderServiceImpl;
import ir.ut.ece.ie.service.user.UserServiceImpl;

public class Dispatcher {
    private final UserController userController;
    private final ProviderController providerController;
    private final CommodityController commodityController;
    private final BuyListController buyListController;
    private final Gson gson;

    public Dispatcher() {
        GsonBuilder builder = new GsonBuilder();
        this.gson = builder.create();
        UserRepository userRepository = UserRepositoryImpl.getInstance();
        ProviderRepository providerRepository = new ProviderRepositoryImpl();
        CommodityRepository commodityRepository = CommodityRepositoryImpl.getInstance();
        ScoreRepository scoreRepository = new ScoreRepositoryImpl();
        BuyListRepository buyListRepository = new BuyListRepositoryImpl();
        this.userController = new UserController(new UserServiceImpl(userRepository));
        this.providerController = new ProviderController(new ProviderServiceImpl(providerRepository));
        this.buyListController = new BuyListController(new BuyListServiceImpl(userRepository, commodityRepository,
                buyListRepository));
        this.commodityController = new CommodityController(new CommodityServiceImpl(commodityRepository,
                scoreRepository, providerRepository, userRepository));
    }

    public Object dispatch(String request) {
        String[] parts = request.split(" ");
        String command = parts[0];
        String data = parts.length == 2 ? parts[1] : null;
        switch (command) {
            case "addUser":
                return userController.addUser(gson.fromJson(data, User.class));
            case "addProvider":
                return providerController.addProvider(gson.fromJson(data, Provider.class));
            case "addCommodity":
                return commodityController.addCommodity(gson.fromJson(data, Commodity.class));
            case "getCommodities":
                return commodityController.getCommodities();
            case "rateCommodity":
                return commodityController.rateCommodity(gson.fromJson(data, Score.class));
            case "getCommodityById":
                return commodityController.getCommodityById(gson.fromJson(data, JsonObject.class).get("id").getAsLong());
            case "getCommoditiesByCategory":
                return commodityController.getCommoditiesByCategory(gson.fromJson(data, JsonObject.class).get("category").getAsString());
            case "addToBuyList":
                return buyListController.addToBuyList(gson.fromJson(data, JsonObject.class).get("username").getAsString(),
                        gson.fromJson(data, JsonObject.class).get("commodityId").getAsLong());
            case "getBuyList":
                return buyListController.getBuyList(gson.fromJson(data, JsonObject.class).get("username").getAsString());
            case "removeFromBuyList":
                buyListController.removeFromBuyList(gson.fromJson(data, JsonObject.class)
                        .get("username").getAsString(), gson.fromJson(data, JsonObject.class).get("commodityId").getAsLong());
                return "commodity removed from buy list";
            default:
                throw new OnlineShopException("Command not defined");
        }
    }
}
