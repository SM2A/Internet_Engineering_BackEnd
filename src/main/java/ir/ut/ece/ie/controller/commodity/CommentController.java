package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.api.dto.CommentDTO;
import ir.ut.ece.ie.service.commodity.CommentService;
import ir.ut.ece.ie.service.commodity.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    private final CommentService commentService;
    private final VoteService voteService;

    @PostMapping
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @GetMapping
    public List<CommentDTO> getCommentsOfCommodity(@RequestParam Long commodityId) {
//        return commentService.getCommentsOfCommodity(commodityId).forEach(comment -> {
//            comment.setLikes(voteService.getNumberOfLikesOfComment(comment.get));
//            comment.setDislikes(voteService.getNumberOfDislikesOfComment(comment.getId()));
//            return comment;
//        }).toList();
        return commentService.getCommentsOfCommodity(commodityId);
    }

}
