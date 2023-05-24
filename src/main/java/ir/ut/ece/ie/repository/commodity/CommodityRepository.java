package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

    Iterable<Commodity> findAllByProviderId(Integer id);

    Iterable<Commodity> findAllByCategoriesContainsIgnoreCase(String category);

    Iterable<Commodity> findAllByNameIsContainingIgnoreCase(String str);

    Iterable<Commodity> findAllByPriceIsBetween(Long from, Long to);
}
