package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteService {
    Vote save(Vote vote);

    List<Vote> getVotesOfComment(Long commentId);

    int getNumberOfLikesOfComment(Long commentId);

    int getNumberOfDislikesOfComment(Long commentId);

    Optional<Vote> findByUsernameAndCommentId(String username, Long commentId);

    void deleteByUsernameAndCommentId(String username, Long commentId);
}
