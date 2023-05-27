package ir.ut.ece.ie.repository.cart;

import ir.ut.ece.ie.domain.cart.BuyItem;
import ir.ut.ece.ie.domain.cart.BuyItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cartRepository extends JpaRepository<BuyItem, BuyItemId> {
    List<BuyItem> findAllById_User_Username(String username);
}
