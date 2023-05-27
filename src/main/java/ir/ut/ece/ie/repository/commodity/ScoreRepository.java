package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Score;
import ir.ut.ece.ie.domain.commodity.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, ScoreId> {

}
