package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Score;

public interface ScoreRepository {
    Score save(Score score);

    Iterable<Score> findAllByCommodityId(Long commodityId);
}
