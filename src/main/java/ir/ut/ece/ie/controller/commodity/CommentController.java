package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Comment;
import ir.ut.ece.ie.service.commodity.CommentService;

import java.util.List;

public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public List<Comment> getCommentsOfCommodity(Long commodityId) {
        return commentService.getCommentsOfCommodity(commodityId);
    }
}
