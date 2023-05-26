package ir.ut.ece.ie.controller.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyItemReq {
    private Long commodityId;
    private Integer quantity;
}
