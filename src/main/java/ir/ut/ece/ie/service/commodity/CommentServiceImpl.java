package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.api.dto.CommentDTO;
import ir.ut.ece.ie.api.mapper.CommentMapper;
import ir.ut.ece.ie.domain.comment.Comment;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.commodity.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;
    private final CommentMapper mapper;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = mapper.toModel(commentDTO);
        comment.setDate(new SimpleDateFormat("yyy-MM-dd").format(Calendar.getInstance().getTime()));
        try {
            comment = repository.save(comment);
        } catch (Exception e) {
            throw new OnlineShopException(e.getMessage());
        }
        return mapper.toDto(comment);
    }

    @Override
    public List<CommentDTO> getCommentsOfCommodity(Long commodityId) {
        List<Comment> comments = (List<Comment>) repository.findAllByCommodityId(commodityId);
        return comments.stream().map(mapper::toDto).toList();
    }
}
