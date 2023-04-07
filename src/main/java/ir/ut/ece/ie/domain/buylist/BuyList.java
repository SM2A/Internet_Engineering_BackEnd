package ir.ut.ece.ie.domain.buylist;

import ir.ut.ece.ie.domain.commodity.Commodity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BuyList {
    private String username;
    private List<Commodity> commodities;
    private Discount discount;
    private Boolean isPaid;

    public BuyList(String username, List<Commodity> commodities) {
        this.username = username;
        this.commodities = commodities;
    }

    public Long getPrice() {
        long totalPrice = commodities.stream().mapToLong(Commodity::getPrice).sum();
        if (discount != null)
            return (long) (totalPrice * (1 - discount.getAmount() / 100.0));
        return totalPrice;
    }
}
