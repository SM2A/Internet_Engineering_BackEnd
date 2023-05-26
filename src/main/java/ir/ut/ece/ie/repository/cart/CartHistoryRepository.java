package ir.ut.ece.ie.repository.cart;

import ir.ut.ece.ie.domain.cart.CartHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public interface CartHistoryRepository extends JpaRepository<CartHistory, Integer> {

}
