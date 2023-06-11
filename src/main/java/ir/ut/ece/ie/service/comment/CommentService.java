package ir.ut.ece.ie.service.comment;

import ir.ut.ece.ie.api.model.comment.CommentDTO;
import ir.ut.ece.ie.api.model.comment.VoteDTO;

import java.util.List;

public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO);

    List<CommentDTO> getCommentsOfCommodity(Long commodityId);

    CommentDTO addVote(Long commentId, VoteDTO voteDTO);
}
