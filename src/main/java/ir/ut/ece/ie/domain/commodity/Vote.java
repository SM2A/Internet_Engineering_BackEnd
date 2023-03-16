package ir.ut.ece.ie.domain.commodity;

import lombok.Data;

import java.util.Objects;

@Data
public class Vote {
    private String username;
    private long commentId;
    private int vote;

    public Vote(String username, long commentId) {
        this.username = username;
        this.commentId = commentId;
    }

    public Vote(String username, long commentId, int vote) {
        this.username = username;
        this.commentId = commentId;
        this.vote = vote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return commentId == vote.commentId && Objects.equals(username, vote.username);
    }
}
