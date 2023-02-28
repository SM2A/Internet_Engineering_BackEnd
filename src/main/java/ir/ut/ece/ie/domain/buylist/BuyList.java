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
}
