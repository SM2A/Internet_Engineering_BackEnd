package ir.ut.ece.ie.domain.commodity;

import lombok.Data;

@Data
public class Score {
    private String username;
    private Long commodityId;
    private Integer score;

    public Score(String username, Long commodityId, Integer score) {
        this.username = username;
        this.commodityId = commodityId;
        this.score = score;
    }
}
