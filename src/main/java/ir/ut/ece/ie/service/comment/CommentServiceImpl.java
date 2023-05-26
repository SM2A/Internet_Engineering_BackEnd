package ir.ut.ece.ie.service.comment;

import ir.ut.ece.ie.api.dto.CommentDTO;
import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.api.mapper.CommentMapper;
import ir.ut.ece.ie.api.mapper.VoteMapper;
import ir.ut.ece.ie.domain.comment.Comment;
import ir.ut.ece.ie.domain.comment.Vote;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.comment.CommentRepository;
import ir.ut.ece.ie.repository.comment.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final VoteRepository voteRepository;
    private final CommentMapper commentMapper;
    private final VoteMapper voteMapper;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toModel(commentDTO);
        comment.setDate(new SimpleDateFormat("yyy-MM-dd").format(Calendar.getInstance().getTime()));
        comment.setLikes(0);
        comment.setDislikes(0);
        try {
            comment = commentRepository.save(comment);
        } catch (Exception e) {
            throw new OnlineShopException(e.getMessage());
        }
        return commentMapper.toDto(comment);
    }

    @Override
    public List<CommentDTO> getCommentsOfCommodity(Long commodityId) {
        List<Comment> comments = (List<Comment>) commentRepository.findAllByCommodityId(commodityId);
        return comments.stream().map(commentMapper::toDto).toList();
    }

    @Override
    public CommentDTO addVote(Long commentId, VoteDTO voteDTO) {
        Vote vote = voteMapper.toModel(voteDTO);
        vote.getVoteId().getComment().setId(commentId);
        try {
            vote = voteRepository.save(vote);
        } catch (Exception e) {
            throw new OnlineShopException(e.getMessage());
        }
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        updateLikesAndDislikes(comment);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    private void updateLikesAndDislikes(Comment comment) {
        comment.setLikes((int) comment.getVotes().stream()
                .filter(vote -> vote.getVote().equals(Vote.Status.LIKE)).count());
        comment.setDislikes((int) comment.getVotes().stream()
                .filter(vote -> vote.getVote().equals(Vote.Status.DISLIKE)).count());
    }
}
