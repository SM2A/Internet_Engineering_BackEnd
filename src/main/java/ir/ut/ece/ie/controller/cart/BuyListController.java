package ir.ut.ece.ie.controller.cart;

import ir.ut.ece.ie.controller.cart.dto.BuyItemReq;
import ir.ut.ece.ie.domain.cart.BuyItem;
import ir.ut.ece.ie.domain.cart.BuyList;
import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.cart.BuyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BuyListController {
    private final BuyListService buyListService;

    @PutMapping("/{username}/buyList")
    public Commodity addToBuyList(@PathVariable String username, @RequestBody BuyItemReq buyItemReq) {
        return buyListService.addToBuyList(username, buyItemReq);
    }

    @GetMapping("/{username}/buyList")
    public BuyList getBuyList(@PathVariable String username) {
        return buyListService.getBuyList(username).orElse(new BuyList(username, new ArrayList<>()));
    }

    @DeleteMapping("/{username}/buyList")
    public void removeFromBuyList(@PathVariable String username, @RequestBody BuyItemReq buyItemReq) {
        buyListService.removeFromBuyList(username, buyItemReq);
    }

    @PutMapping("{username}/buyList/discount")
    public void applyDiscount(@PathVariable String username, @RequestParam String code) {
        buyListService.applyDiscount(getBuyList(username), code);
    }

    @PostMapping("/buyList/pay")
    public void pay(@RequestParam String username) {
        BuyList buyList = buyListService.getBuyList(username).orElseThrow(() ->
                new OnlineShopException("User doesn't have any buy list"));
        buyListService.pay(buyList);
    }
    @GetMapping("/purchased")
    public List<BuyItem> getPurchasedCommodities(@RequestParam String username) {
        return buyListService.getPurchasedItems(username);
    }
}
