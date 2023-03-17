package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Comment;

public interface CommentRepository {
    Comment save(Comment comment);

    Iterable<Comment> saveAll(Iterable<Comment> comments);

    Iterable<Comment> findAllByCommodityId(Long commodityId);
}
