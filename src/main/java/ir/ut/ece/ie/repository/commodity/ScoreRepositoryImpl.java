package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Score;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScoreRepositoryImpl implements ScoreRepository {
    private static ScoreRepositoryImpl INSTANCE = null;
    private final Map<AbstractMap.SimpleEntry<String, Long>, Score> scores = new HashMap<>();

    private ScoreRepositoryImpl() {

    }

    public static ScoreRepositoryImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ScoreRepositoryImpl();
        return INSTANCE;
    }

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
                .filter(score -> Objects.equals(score.getCommodityId(), commodityId))
                .toList();
    }
}
