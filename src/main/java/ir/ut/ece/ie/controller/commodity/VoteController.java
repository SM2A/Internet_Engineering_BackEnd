package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.commodity.VoteService;
import ir.ut.ece.ie.service.user.UserService;

import java.util.Optional;

public class VoteController {
    private final VoteService voteService;
    private final UserService userService;

    public VoteController(VoteService voteService, UserService userService) {
        this.voteService = voteService;
        this.userService = userService;
    }

    public Vote addVote(Vote vote) {
        Optional<User> user = userService.getUser(vote.getUsername());
        if (user.isEmpty())
            throw new OnlineShopException("User not found");
        if (voteService.findByUsernameAndCommentId(vote.getUsername(), vote.getCommentId()).isPresent())
            voteService.deleteByUsernameAndCommentId(vote.getUsername(), vote.getCommentId());
        return voteService.save(vote);
    }
}
