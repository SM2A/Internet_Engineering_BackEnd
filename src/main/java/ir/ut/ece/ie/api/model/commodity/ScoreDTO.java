package ir.ut.ece.ie.api.model.commodity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ScoreDTO {
    private String username;
    private Long commodityId;
    private Integer score;
}
