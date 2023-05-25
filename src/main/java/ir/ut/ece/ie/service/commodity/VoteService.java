package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.domain.commodity.Vote;

public interface VoteService {
    Vote addVote(VoteDTO voteDTO);
}
