package ir.ut.ece.ie.service.commodity;

import ir.ut.ece.ie.api.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO addComment(CommentDTO commentDTO);

    List<CommentDTO> getCommentsOfCommodity(Long commodityId);
}
