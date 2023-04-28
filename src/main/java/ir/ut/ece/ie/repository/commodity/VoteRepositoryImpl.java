package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class VoteRepositoryImpl implements VoteRepository {
    private final Map<Long, ArrayList<Vote>> votes = new HashMap<>();

    @Override
    public Vote save(Vote vote) {
        if (!votes.containsKey(vote.getCommentId())) votes.put(vote.getCommentId(), new ArrayList<>());
        votes.get(vote.getCommentId()).add(vote);
        return vote;
    }

    @Override
    public void deleteByUsernameAndCommentId(String username, Long commentId) {
        votes.get(commentId).remove(new Vote(username, commentId));
    }

    @Override
    public Iterable<Vote> findAllByCommentId(Long commentId) {
        if (votes.containsKey(commentId)) return votes.get(commentId).stream().toList();
        else return new ArrayList<>();
    }

    @Override
    public Optional<Vote> findByUsernameAndCommentId(String username, Long commentId) {
        if (votes.containsKey(commentId)) {
            return votes.get(commentId).stream()
                    .filter(v -> v.getUsername().equals(username))
                    .findFirst();
        }
        return Optional.empty();
    }
}
