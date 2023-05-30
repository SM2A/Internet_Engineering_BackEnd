package ir.ut.ece.ie.api.model.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BuyItemDTO {
    private String username;
    private Long commodityId;
    private Integer quantity;
}
