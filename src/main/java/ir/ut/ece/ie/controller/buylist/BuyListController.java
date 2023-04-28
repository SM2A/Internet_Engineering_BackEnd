package ir.ut.ece.ie.controller.buylist;

import ir.ut.ece.ie.domain.buylist.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.service.buylist.BuyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BuyListController {
    private final BuyListService buyListService;

    @PutMapping("/{username}/buyList")
    public Commodity addToBuyList(@PathVariable String username, @RequestParam Long commodityId) {
        return buyListService.addToBuyList(username, commodityId);
    }

    @GetMapping("/{username}/buyList")
    public BuyList getBuyList(@PathVariable String username) {
        return buyListService.getBuyList(username).orElse(new BuyList(username, new ArrayList<>()));
    }

    @DeleteMapping("/{username}/buyList")
    public void removeFromBuyList(@PathVariable String username, @RequestParam Long commodityId) {
        buyListService.removeFromBuyList(username, commodityId);
    }

    @PutMapping("{username}/buyList/discount")
    public void applyDiscount(@PathVariable String username, @RequestParam String code) {
        buyListService.applyDiscount(getBuyList(username), code);
    }

    @PostMapping("/buyList/pay")
    public void pay(@RequestBody BuyList buyList) {
        buyListService.pay(buyList);
    }
}
