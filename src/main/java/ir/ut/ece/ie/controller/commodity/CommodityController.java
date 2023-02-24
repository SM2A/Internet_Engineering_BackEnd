package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.service.commodity.CommodityService;

public class CommodityController {
    private CommodityService commodityService;

    public CommodityController(CommodityService service) {
        this.commodityService = service;
    }

    public Commodity addCommodity(Commodity commodity) {
        return commodityService.addCommodity(commodity);
    }

}
