package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Score;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ScoreRepositoryImpl implements ScoreRepository {
    private Map<AbstractMap.SimpleEntry<String, Long>, Score> scores = new HashMap<>();

    @Override
    public Score save(Score score) {
        AbstractMap.SimpleEntry<String, Long> entryKey =
                new AbstractMap.SimpleEntry<>(score.getUsername(), score.getCommodityId());
        scores.put(entryKey, score);
        return score;
    }

    @Override
    public Iterable<Score> findAllByCommodityId(Long commodityId) {
        return scores.values().stream()
                .filter(score -> score.getCommodityId() == commodityId)
                .toList();
    }
}
