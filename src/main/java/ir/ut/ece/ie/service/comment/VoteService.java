package ir.ut.ece.ie.service.comment;

import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.domain.comment.Vote;

public interface VoteService {
    Vote addVote(VoteDTO voteDTO);
}
