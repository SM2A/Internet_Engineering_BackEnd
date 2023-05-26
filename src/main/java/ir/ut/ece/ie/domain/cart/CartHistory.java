package ir.ut.ece.ie.domain.cart;

import ir.ut.ece.ie.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class CartHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "cart_history_id"))
    @Column(name = "item")
    private List<Item> items;
}
