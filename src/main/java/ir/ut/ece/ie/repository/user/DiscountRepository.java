package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.user.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Optional<Discount> findDiscountByCode(String code);
}
