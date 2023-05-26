package ir.ut.ece.ie.domain.cart;

import ir.ut.ece.ie.domain.commodity.Commodity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Item implements Serializable {
    @OneToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;
    private Integer quantity;
}
