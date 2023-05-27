package ir.ut.ece.ie.repository.comment;

import ir.ut.ece.ie.domain.comment.Vote;
import ir.ut.ece.ie.domain.comment.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VoteId> {
}
