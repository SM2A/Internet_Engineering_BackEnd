package ir.ut.ece.ie.domain.cart;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class BuyItem {
    @EmbeddedId
    private BuyItemId id;

    @Column(nullable = false)
    private Integer quantity;

    public Long getPrice() {
        return id.getCommodity().getPrice() * quantity;
    }

}
