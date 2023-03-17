package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.service.commodity.VoteService;

public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    public Vote addVote(Vote vote) {
        if (voteService.findByUsernameAndCommentId(vote.getUsername(), vote.getCommentId()).isPresent())
            voteService.deleteByUsernameAndCommentId(vote.getUsername(), vote.getCommentId());
        return voteService.save(vote);
    }
}
