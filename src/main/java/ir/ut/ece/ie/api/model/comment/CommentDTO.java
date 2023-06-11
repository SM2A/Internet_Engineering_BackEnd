package ir.ut.ece.ie.api.model.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String username;
    private Long commodityId;
    private String text;
    private String date;
    private Integer likes;
    private Integer dislikes;
}
