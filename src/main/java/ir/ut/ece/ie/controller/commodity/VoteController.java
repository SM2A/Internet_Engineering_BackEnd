package ir.ut.ece.ie.controller.commodity;

import ir.ut.ece.ie.api.dto.VoteDTO;
import ir.ut.ece.ie.domain.commodity.Vote;
import ir.ut.ece.ie.service.commodity.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes")
@CrossOrigin(origins = "http://localhost:3000")
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public Vote addVote(@RequestBody VoteDTO voteDTO) {
        return voteService.addVote(voteDTO);
    }
}
