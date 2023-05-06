package ir.ut.ece.ie.domain.buylist;

import ir.ut.ece.ie.domain.commodity.Commodity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyItem {
    private Commodity commodity;
    private Integer quantity;

    public Long getPrice() {
        return commodity.getPrice() * quantity;
    }
}
