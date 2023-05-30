package ir.ut.ece.ie.controller.comment;

import ir.ut.ece.ie.api.model.comment.CommentDTO;
import ir.ut.ece.ie.api.model.comment.VoteDTO;
import ir.ut.ece.ie.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @GetMapping
    public List<CommentDTO> getCommentsOfCommodity(@RequestParam Long commodityId) {
        return commentService.getCommentsOfCommodity(commodityId);
    }

    @PostMapping("/{commentId}/vote")
    public CommentDTO addVote(@PathVariable Long commentId, @RequestBody VoteDTO voteDTO) {
        return commentService.addVote(commentId, voteDTO);
    }

}
