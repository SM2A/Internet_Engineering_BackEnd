package ir.ut.ece.ie.service.comment;

import ir.ut.ece.ie.api.dto.CommentDTO;
import ir.ut.ece.ie.api.mapper.CommentMapper;
import ir.ut.ece.ie.domain.comment.Comment;
import ir.ut.ece.ie.domain.comment.Vote;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toModel(commentDTO);
        comment.setDate(new SimpleDateFormat("yyy-MM-dd").format(Calendar.getInstance().getTime()));
        comment.setLikes(0);
        comment.setDislikes(0);
        try {
            comment = repository.save(comment);
        } catch (Exception e) {
            throw new OnlineShopException(e.getMessage());
        }
        return commentMapper.toDto(comment);
    }

    @Override
    public List<CommentDTO> getCommentsOfCommodity(Long commodityId) {
        List<Comment> comments = (List<Comment>) repository.findAllByCommodityId(commodityId);
        comments.forEach(comment -> {
            comment.setLikes((int) comment.getVotes().stream()
                    .filter(vote -> vote.getVote().equals(Vote.Status.LIKE)).count());
            comment.setDislikes((int) comment.getVotes().stream()
                    .filter(vote -> vote.getVote().equals(Vote.Status.DISLIKE)).count());
        });
        return comments.stream().map(commentMapper::toDto).toList();
    }

}
