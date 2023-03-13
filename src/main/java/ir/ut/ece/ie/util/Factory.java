package ir.ut.ece.ie.util;

import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.repository.commodity.*;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import ir.ut.ece.ie.service.commodity.CommodityServiceImpl;

public class Factory {
    private static CommodityRepository commodityRepository = new CommodityRepositoryImpl();
    private static CommentRepository commentRepository = new CommentRepositoryImpl();
    private static ScoreRepository scoreRepository = new ScoreRepositoryImpl();
    private static ProviderRepository providerRepository = new ProviderRepositoryImpl();
    private static UserRepository userRepository = new UserRepositoryImpl();
    private static CommodityController commodityController = new CommodityController(
            new CommodityServiceImpl(
                    commodityRepository,
                    scoreRepository,
                    providerRepository,
                    userRepository)
    );

    public static CommodityRepository getCommodityRepository() {
        return commodityRepository;
    }

    public static CommentRepository getCommentRepository() {
        return commentRepository;
    }

    public static ScoreRepository getScoreRepository() {
        return scoreRepository;
    }

    public static ProviderRepository getProviderRepository() {
        return providerRepository;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static CommodityController getCommodityController() {
        return commodityController;
    }
}
