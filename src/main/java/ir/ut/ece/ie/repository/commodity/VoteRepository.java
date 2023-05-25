package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.domain.commodity.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VoteId> {
}
