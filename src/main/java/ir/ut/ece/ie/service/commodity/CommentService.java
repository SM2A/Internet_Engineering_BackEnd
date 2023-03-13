package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsOfCommodity(Long commodityId);
}
