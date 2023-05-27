package ir.ut.ece.ie.controller.comment;

import ir.ut.ece.ie.api.dto.CommentDTO;
import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
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
