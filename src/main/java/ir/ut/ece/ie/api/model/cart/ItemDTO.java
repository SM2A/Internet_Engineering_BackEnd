package ir.ut.ece.ie.api.model.cart;

import ir.ut.ece.ie.api.model.commodity.CommodityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
    private CommodityDTO commodity;
    private Integer quantity;
}
