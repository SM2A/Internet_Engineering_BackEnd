package ir.ut.ece.ie.api.dto;

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
