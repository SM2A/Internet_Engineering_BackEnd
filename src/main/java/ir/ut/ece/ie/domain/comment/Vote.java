package ir.ut.ece.ie.domain.comment;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Entity
public class Vote {

    @EmbeddedId
    private VoteId voteId;

    @Enumerated(EnumType.ORDINAL)
    private Status vote;

    public enum Status {
        DISLIKE,
        LIKE
    }

}
