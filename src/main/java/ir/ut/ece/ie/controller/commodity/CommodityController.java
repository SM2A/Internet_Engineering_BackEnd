package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.api.model.commodity.CommodityDTO;
import ir.ut.ece.ie.api.model.commodity.ScoreDTO;
import ir.ut.ece.ie.service.commodity.CommodityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commodities")
public class CommodityController {
    private final CommodityService commodityService;

    @PostMapping
    public CommodityDTO addCommodity(@RequestBody CommodityDTO commodityDTO) {
        return commodityService.addCommodity(commodityDTO);
    }

    @GetMapping("/{id}")
    public CommodityDTO getCommodityById(@PathVariable Long id) {
        return commodityService.getCommodityById(id);
    }

    @GetMapping
    public List<CommodityDTO> getCommodities() {
        return commodityService.getCommodities();
    }

    @PutMapping("/rate")
    public CommodityDTO rateCommodity(@RequestBody ScoreDTO score) {
        return commodityService.rateCommodity(score);
    }

    @GetMapping("/suggestions/{id}")
    public List<CommodityDTO> getSuggestedCommodities(@PathVariable Long id) {
        return commodityService.getSuggestedCommodities(id);
    }

    @GetMapping("/provider")
    public List<CommodityDTO> getCommoditiesByProviderId(@RequestParam Integer id) {
        return commodityService.getCommoditiesByProviderId(id);
    }

    @GetMapping("/category")
    public List<CommodityDTO> getCommoditiesByCategory(@RequestParam String category) {
        return commodityService.getCommoditiesByCategory(category);
    }

    @GetMapping("/name")
    public List<CommodityDTO> getCommoditiesByNameContains(@RequestParam String searchStr) {
        return commodityService.getCommoditiesByNameContains(searchStr);
    }

    @GetMapping("/price")
    public List<CommodityDTO> getCommoditiesInPriceRange(@RequestParam Long from, @RequestParam Long to) {
        return commodityService.getCommoditiesInPriceRange(from, to);
    }
}
