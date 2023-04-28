package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.buylist.Discount;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class DiscountRepositoryImpl implements DiscountRepository {
    private final Map<String, Discount> discounts = new HashMap<>();

    @Override
    public Discount save(Discount discount) {
        discounts.put(discount.getCode(), discount);
        return discount;
    }

    @Override
    public Iterable<Discount> saveAll(Iterable<Discount> discounts) {
        discounts.forEach(discount -> this.discounts.put(discount.getCode(), discount));
        return discounts;
    }

    @Override
    public Optional<Discount> findByCode(String code) {
        return Optional.ofNullable(discounts.get(code));
    }
}
