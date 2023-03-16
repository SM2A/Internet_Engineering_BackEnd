package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;

import java.util.Optional;

public interface VoteRepository {

    Vote save(Vote vote);

    void delete(String username, long commentId);

    Iterable<Vote> findAllByComment(long commentId);

    Optional<Vote> findSpecific(String username, long commentId);
}
