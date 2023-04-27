package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Comment;
import ir.ut.ece.ie.service.commodity.CommentService;
import ir.ut.ece.ie.service.commodity.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final VoteService voteService;

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping
    public List<Comment> getCommentsOfCommodity(@RequestParam Long commodityId) {
        return commentService.getCommentsOfCommodity(commodityId).stream().map(comment -> {
            comment.setLikes(voteService.getNumberOfLikesOfComment(comment.getId()));
            comment.setDislikes(voteService.getNumberOfDislikesOfComment(comment.getId()));
            return comment;
        }).toList();
    }

}
