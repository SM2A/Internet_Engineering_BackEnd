package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;

import java.util.List;

public interface VoteService {
    Vote save(Vote vote);

    List<Vote> getVotesOfComment(long commentId);

    int likesOfComment(long commentId);

    int dislikesOfComment(long commentId);
}
