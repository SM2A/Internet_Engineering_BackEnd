package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.domain.commodity.Comment;
import ir.ut.ece.ie.repository.commodity.CommentRepository;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getCommentsOfCommodity(Long commodityId) {
        return (List<Comment>) commentRepository.findAllByCommodityId(commodityId);
    }
}
