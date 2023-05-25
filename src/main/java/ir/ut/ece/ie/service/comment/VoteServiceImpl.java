package ir.ut.ece.ie.service.comment;

import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.api.mapper.VoteMapper;
import ir.ut.ece.ie.domain.comment.Vote;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.comment.CommentRepository;
import ir.ut.ece.ie.repository.comment.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;
    private final VoteMapper mapper;

    @Override
    public Vote addVote(VoteDTO voteDTO) {
        Vote vote = mapper.toModel(voteDTO);
        try {
            vote = voteRepository.save(vote);
        } catch (Exception e) {
            throw new OnlineShopException(e.getMessage());
        }
        return vote;
    }
}