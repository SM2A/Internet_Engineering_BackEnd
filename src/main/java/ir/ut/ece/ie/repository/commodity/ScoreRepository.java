package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Score;

import java.util.Optional;

public interface ScoreRepository {

    Score save(Score score);

    Iterable<Score> findAllByCommodityId(Long commodityId);

    Optional<Score> findUserRatingCommodity(Long commodityId, String username);

    void deleteUserRating(Long commodityId, String username);
}
