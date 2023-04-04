package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Comment;
import ir.ut.ece.ie.service.commodity.CommentService;
import ir.ut.ece.ie.service.commodity.VoteService;

import java.util.List;

public class CommentController {
    private final CommentService commentService;
    private final VoteService voteService;

    public CommentController(CommentService commentService, VoteService voteService) {
        this.commentService = commentService;
        this.voteService = voteService;
    }

    public Comment addComment(Comment comment) {
        return commentService.addComment(comment);
    }

    public List<Comment> getCommentsOfCommodity(Long commodityId) {
        return commentService.getCommentsOfCommodity(commodityId).stream().map(comment -> {
            comment.setLikes(voteService.getNumberOfLikesOfComment(comment.getId()));
            comment.setDislikes(voteService.getNumberOfDislikesOfComment(comment.getId()));
            return comment;
        }).toList();
    }

}
