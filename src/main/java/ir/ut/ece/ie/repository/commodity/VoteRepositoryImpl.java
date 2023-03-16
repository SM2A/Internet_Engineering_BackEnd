package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;

import java.util.*;

public class VoteRepositoryImpl implements VoteRepository {
    private final Map<Long, ArrayList<Vote>> votes = new HashMap<>();

    @Override
    public Vote save(Vote vote) {
        if (!votes.containsKey(vote.getCommentId())) votes.put(vote.getCommentId(), new ArrayList<>());
        votes.get(vote.getCommentId()).add(vote);
        return vote;
    }

    @Override
    public void delete(String username, long commentId) {
        votes.get(commentId).remove(new Vote(username, commentId));
    }

    @Override
    public Iterable<Vote> findAllByComment(long commentId) {
        if (votes.containsKey(commentId)) return votes.get(commentId).stream().toList();
        else return new ArrayList<>();
    }

    @Override
    public Optional<Vote> findSpecific(String username, long commentId) {
        Vote vote = votes.get(commentId).stream()
                .filter(v -> v.getUsername().equals(username))
                .findFirst().orElse(null);
        if (vote != null) return Optional.of(vote);
        return Optional.empty();
    }
}
