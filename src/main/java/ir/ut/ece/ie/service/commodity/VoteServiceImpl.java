package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.repository.commodity.VoteRepository;

import java.util.List;
import java.util.Optional;

public class VoteServiceImpl implements VoteService{
    private final VoteRepository voteRepository;

    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> getVotesOfComment(long commentId) {
        return (List<Vote>) voteRepository.findAllByComment(commentId);
    }

    @Override
    public int likesOfComment(long commentId) {
        return getVotesOfComment(commentId).stream().filter(vote -> vote.getVote() == Vote.Status.LIKE).toList().size();
    }

    @Override
    public int dislikesOfComment(long commentId) {
        return getVotesOfComment(commentId).stream().filter(vote -> vote.getVote() == Vote.Status.DISLIKE).toList().size();
    }

    @Override
    public Optional<Vote> findSpecific(String username, long commentId) {
        return voteRepository.findSpecific(username, commentId);
    }

    @Override
    public void delete(String username, long commentId) {
        voteRepository.delete(username, commentId);
    }
}
