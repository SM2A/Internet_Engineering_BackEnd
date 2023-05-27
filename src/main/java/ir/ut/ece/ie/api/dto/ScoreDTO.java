package ir.ut.ece.ie.api.dto;

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
