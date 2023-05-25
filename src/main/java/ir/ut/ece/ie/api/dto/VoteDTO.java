package ir.ut.ece.ie.api.dto;

import ir.ut.ece.ie.domain.commodity.Vote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VoteDTO {
    private String username;
    private Long commentId;
    private Vote.Status vote;
}
