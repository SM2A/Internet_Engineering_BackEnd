package ir.ut.ece.ie.repository.commodity;

import ir.ut.ece.ie.domain.commodity.Comment;
import ir.ut.ece.ie.util.SequenceGenerator;

import java.util.HashMap;
import java.util.Map;

public class CommentRepositoryImpl implements CommentRepository {
    private final Map<Long, Comment> comments = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        comment.setId(SequenceGenerator.getNext());
        comments.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public Iterable<Comment> saveAll(Iterable<Comment> comments) {
        comments.forEach(comment -> comment.setId(SequenceGenerator.getNext()));
        comments.forEach(comment -> this.comments.put(comment.getId(), comment));
        return comments;
    }

    @Override
    public Iterable<Comment> findAllByCommodityId(Long commodityId) {
        return comments.values().stream()
                .filter(comment -> comment.getCommodityId() == commodityId)
                .toList();
    }
}
