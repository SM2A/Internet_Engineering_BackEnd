package ir.ut.ece.ie.api.model.comment;

import ir.ut.ece.ie.domain.comment.Vote;
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
