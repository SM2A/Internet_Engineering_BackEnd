package ir.ut.ece.ie.api.model.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CartHistoryDTO {
    private Long id;
    private String username;
    private List<ItemDTO> items;
}
