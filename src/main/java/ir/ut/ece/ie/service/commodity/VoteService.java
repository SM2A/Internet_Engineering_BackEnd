package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteService {
    Vote save(Vote vote);

    List<Vote> getVotesOfComment(long commentId);

    int likesOfComment(long commentId);

    int dislikesOfComment(long commentId);

    Optional<Vote> findSpecific(String username, long commentId);

    void delete(String username, long commentId);
}
