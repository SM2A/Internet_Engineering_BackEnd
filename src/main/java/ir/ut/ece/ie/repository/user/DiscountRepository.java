package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.buylist.Discount;

import java.util.Optional;

public interface DiscountRepository {
    Discount save(Discount discount);
    Iterable<Discount> saveAll(Iterable<Discount> discounts);
    Optional<Discount> findByCode(String code);
}
