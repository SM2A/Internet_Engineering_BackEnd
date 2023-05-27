package ir.ut.ece.ie.domain.cart;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class BuyItemId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;
}
