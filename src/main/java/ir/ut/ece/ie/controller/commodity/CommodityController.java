package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.commodity.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commodities")
@CrossOrigin(origins = "http://localhost:3000")
public class CommodityController {
    private final CommodityService commodityService;

    @PostMapping
    public Commodity addCommodity(@RequestBody Commodity commodity) {
        return commodityService.addCommodity(commodity);
    }

    @GetMapping("/{id}")
    public Commodity getCommodityById(@PathVariable Long id) {
        return commodityService.getCommodityById(id).orElseThrow(() -> new OnlineShopException("commodity not found"));
    }

    @GetMapping
    public List<Commodity> getCommodities() {
        return commodityService.getCommodities();
    }

    @PutMapping("/rate")
    public Commodity rateCommodity(@RequestBody Score score) {
        return commodityService.rateCommodity(score);
    }

    @GetMapping("/suggestions/{id}")
    public List<Commodity> getSuggestedCommodities(@PathVariable Long id) {
        return commodityService.getSuggestedCommodities(id);
    }

    @GetMapping("/provider")
    public List<Commodity> getCommoditiesByProviderId(@RequestParam Integer id) {
        return commodityService.getCommoditiesByProviderId(id);
    }

    @GetMapping("/category")
    public List<Commodity> getCommoditiesByCategory(@RequestParam String category) {
        return commodityService.getCommoditiesByCategory(category);
    }

    @GetMapping("/name")
    public List<Commodity> getCommoditiesByNameContains(@RequestParam String searchStr) {
        return commodityService.getCommoditiesByNameContains(searchStr);
    }

    @GetMapping("/price")
    public List<Commodity> getCommoditiesInPriceRange(@RequestParam Long from, @RequestParam Long to) {
        return commodityService.getCommoditiesInPriceRange(from, to);
    }
}
