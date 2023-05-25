package ir.ut.ece.ie.domain.comment;

import ir.ut.ece.ie.domain.commodity.Commodity;
import ir.ut.ece.ie.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commodity_id", nullable = false)
    private Commodity commodity;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column
    private String text;

    @Column
    private String date;

    @Column
    private Integer likes = 0;

    @Column
    private Integer dislikes = 0;
}
