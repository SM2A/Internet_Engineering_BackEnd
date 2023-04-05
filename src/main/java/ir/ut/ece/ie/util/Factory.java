package ir.ut.ece.ie.util;

import ir.ut.ece.ie.controller.buylist.BuyListController;
import ir.ut.ece.ie.controller.commodity.CommentController;
import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.controller.commodity.VoteController;
import ir.ut.ece.ie.controller.provider.ProviderController;
import ir.ut.ece.ie.controller.user.UserController;
import ir.ut.ece.ie.repository.buylist.BuyListRepository;
import ir.ut.ece.ie.repository.buylist.BuyListRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.*;
import ir.ut.ece.ie.repository.provider.ProviderRepository;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.DiscountRepository;
import ir.ut.ece.ie.repository.user.DiscountRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import ir.ut.ece.ie.service.buylist.BuyListServiceImpl;
import ir.ut.ece.ie.service.commodity.CommentServiceImpl;
import ir.ut.ece.ie.service.commodity.CommodityService;
import ir.ut.ece.ie.service.commodity.CommodityServiceImpl;
import ir.ut.ece.ie.service.commodity.VoteServiceImpl;
import ir.ut.ece.ie.service.provider.ProviderServiceImpl;
import ir.ut.ece.ie.service.user.UserService;
import ir.ut.ece.ie.service.user.UserServiceImpl;

public class Factory {
    private static final CommodityRepository commodityRepository = new CommodityRepositoryImpl();
    private static final CommentRepository commentRepository = new CommentRepositoryImpl();
    private static final VoteRepository voteRepository = new VoteRepositoryImpl();
    private static final ScoreRepository scoreRepository = new ScoreRepositoryImpl();
    private static final ProviderRepository providerRepository = new ProviderRepositoryImpl();
    private static final UserRepository userRepository = new UserRepositoryImpl();
    private static final BuyListRepository buyListRepository = new BuyListRepositoryImpl();
    private static final DiscountRepository discountRepository = new DiscountRepositoryImpl();
    private static final UserService userService = new UserServiceImpl(userRepository);
    private static final CommodityService commodityService = new CommodityServiceImpl(commodityRepository, scoreRepository,
            providerRepository, userRepository);
    private static final CommodityController commodityController = new CommodityController(commodityService);
    private static final BuyListController buyListController = new BuyListController(
            new BuyListServiceImpl(userService, commodityService, buyListRepository, discountRepository));
    private static final CommentController commentController = new CommentController(new CommentServiceImpl(commentRepository),
            new VoteServiceImpl(voteRepository));
    private static final ProviderController providerController = new ProviderController(new ProviderServiceImpl(providerRepository));
    private static final UserController userController = new UserController(new UserServiceImpl(userRepository));
    private static final VoteController voteController = new VoteController(new VoteServiceImpl(voteRepository),
            new UserServiceImpl(userRepository));

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

    public static BuyListRepository getBuyListRepository() {
        return buyListRepository;
    }

    public static DiscountRepository getDiscountRepository() {
        return discountRepository;
    }

    public static CommodityController getCommodityController() {
        return commodityController;
    }

    public static CommentController getCommentController() {
        return commentController;
    }

    public static ProviderController getProviderController() {
        return providerController;
    }

    public static UserController getUserController() {
        return userController;
    }

    public static BuyListController getBuyListController() {
        return buyListController;
    }

    public static VoteController getVoteController() {
        return voteController;
    }
}
