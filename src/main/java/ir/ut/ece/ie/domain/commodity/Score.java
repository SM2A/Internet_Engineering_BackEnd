package ir.ut.ece.ie.domain.commodity;

import lombok.Data;

@Data
public class Score {
    private String username;
    private Long commodityId;
    private Integer score;
}
