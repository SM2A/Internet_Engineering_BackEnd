package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long> {

    Iterable<Commodity> findAllByProviderId(Integer id);

    Iterable<Commodity> findAllByCategoriesContainsIgnoreCase(String category);

    Iterable<Commodity> findAllByNameIsContainingIgnoreCase(String str);

    Iterable<Commodity> findAllByPriceIsBetween(Long from, Long to);

    @Query(value = "SELECT avg(s.score) FROM Score s WHERE s.scoreId.commodity.id = :commodityId")
    Double getRating(@Param("commodityId") Long commodityId);

}
