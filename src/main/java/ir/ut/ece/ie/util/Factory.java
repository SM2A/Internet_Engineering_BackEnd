package ir.ut.ece.ie.util;

import ir.ut.ece.ie.controller.commodity.CommodityController;
import ir.ut.ece.ie.repository.commodity.CommodityRepositoryImpl;
import ir.ut.ece.ie.repository.commodity.ScoreRepositoryImpl;
import ir.ut.ece.ie.repository.provider.ProviderRepositoryImpl;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;
import ir.ut.ece.ie.service.commodity.CommodityServiceImpl;

public class Factory {
    private static CommodityController commodityController = new CommodityController(
            new CommodityServiceImpl(
                    CommodityRepositoryImpl.getInstance(),
                    ScoreRepositoryImpl.getInstance(),
                    ProviderRepositoryImpl.getInstance(),
                    UserRepositoryImpl.getInstance())
    );

    public static CommodityController getCommodityController() {
        return commodityController;
    }
}
