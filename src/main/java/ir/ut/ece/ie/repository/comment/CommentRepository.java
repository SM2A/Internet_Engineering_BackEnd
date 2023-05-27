package ir.ut.ece.ie.repository.comment;

import ir.ut.ece.ie.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findAllByCommodityId(Long commodityId);
}
