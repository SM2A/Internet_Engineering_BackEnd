package ir.ut.ece.ie.domain.commodity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Score {

    @EmbeddedId
    private ScoreId scoreId;

    @Column(nullable = false)
    private Integer score;

}
