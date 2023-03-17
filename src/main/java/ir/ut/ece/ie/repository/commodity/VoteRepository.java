package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;

import java.util.Optional;

public interface VoteRepository {

    Vote save(Vote vote);

    void deleteByUsernameAndCommentId(String username, Long commentId);

    Iterable<Vote> findAllByComment(Long commentId);

    Optional<Vote> findByUsernameAndCommentId(String username, Long commentId);
}
