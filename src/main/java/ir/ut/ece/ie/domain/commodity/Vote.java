package ir.ut.ece.ie.domain.commodity;

import lombok.Data;

import java.util.Objects;

@Data
public class Vote {
    private String username;
    private long commentId;
    private Status vote;

    public Vote(String username, long commentId) {
        this.username = username;
        this.commentId = commentId;
    }

    public Vote(String username, long commentId, Status vote) {
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

    public enum Status {
        LIKE(1), DISLIKE(-1);

        public final int value;

        Status(int value) {
            this.value = value;
        }

        public static Status enumOf(int value) {
            return value == 1 ? LIKE : DISLIKE;
        }
    }

}
