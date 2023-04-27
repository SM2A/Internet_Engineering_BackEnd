package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.repository.commodity.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;

    @Override
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> getVotesOfComment(Long commentId) {
        return (List<Vote>) voteRepository.findAllByCommentId(commentId);
    }

    @Override
    public int getNumberOfLikesOfComment(Long commentId) {
        return getVotesOfComment(commentId).stream().filter(vote -> vote.getVote() == Vote.Status.LIKE).toList().size();
    }

    @Override
    public int getNumberOfDislikesOfComment(Long commentId) {
        return getVotesOfComment(commentId).stream().filter(vote -> vote.getVote() == Vote.Status.DISLIKE).toList().size();
    }

    @Override
    public Optional<Vote> findByUsernameAndCommentId(String username, Long commentId) {
        return voteRepository.findByUsernameAndCommentId(username, commentId);
    }

    @Override
    public void deleteByUsernameAndCommentId(String username, Long commentId) {
        voteRepository.deleteByUsernameAndCommentId(username, commentId);
    }
}
