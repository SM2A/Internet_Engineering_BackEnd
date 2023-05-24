package ir.ut.ece.ie.domain.buylist;

import ir.ut.ece.ie.domain.user.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BuyList {
    private String username;
    private List<BuyItem> buyItems;
    private Discount discount;
    private Boolean isPaid;

    public BuyList(String username, List<BuyItem> buyItems) {
        this.username = username;
        this.buyItems = buyItems;
    }

    public Long getPrice() {
        long totalPrice = buyItems.stream()
                .mapToLong(BuyItem::getPrice)
                .sum();
        if (discount != null)
            return (long) (totalPrice * (1 - discount.getAmount() / 100.0));
        return totalPrice;
    }
}
