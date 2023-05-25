package ir.ut.ece.ie.domain.commodity;

import ir.ut.ece.ie.domain.comment.Comment;
import ir.ut.ece.ie.domain.user.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapping;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class VoteId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
