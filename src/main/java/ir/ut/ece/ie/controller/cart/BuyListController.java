package ir.ut.ece.ie.controller.cart;

import ir.ut.ece.ie.api.dto.BuyItemDTO;
import ir.ut.ece.ie.api.dto.CartHistoryDTO;
import ir.ut.ece.ie.service.cart.BuyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BuyListController {
    private final BuyListService buyListService;

    @PutMapping("/buyList")
    public BuyItemDTO addToBuyList(@RequestBody BuyItemDTO buyItemDTO) {
        return buyListService.addToBuyList(buyItemDTO);
    }

    @GetMapping("/buyList/{username}")
    public List<BuyItemDTO> getBuyList(@PathVariable String username) {
        return buyListService.getBuyList(username);
    }

    @DeleteMapping("/buyList/{username}")
    public void removeFromBuyList(@PathVariable String username, @RequestParam Long commodityId) {
        buyListService.removeFromBuyList(username, commodityId);
    }

    @PutMapping("/buyList/{username}/discount")
    public void applyDiscount(@PathVariable String username, @RequestParam String code) {
        buyListService.applyDiscount(username, code);
    }

    @PostMapping("/buyList/{username}/pay")
    public void pay(@PathVariable String username) {
        buyListService.pay(username);
    }

    @GetMapping("/purchased")
    public List<CartHistoryDTO> getPurchasedCommodities(@RequestParam String username) {
        return buyListService.getPurchasedItems(username);
    }
}
