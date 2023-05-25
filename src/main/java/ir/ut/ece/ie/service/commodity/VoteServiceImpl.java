package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.api.mapper.VoteMapper;
import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommentRepository;
import ir.ut.ece.ie.repository.commodity.VoteRepository;
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