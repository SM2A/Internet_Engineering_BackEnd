package ir.ut.ece.ie.domain.commodity;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long commodityId;
    private String userEmail;
    private String text;
    private String date;
    private Integer likes;
    private Integer dislikes;

    public Comment(Long commodityId, String userEmail, String text) {
        this.commodityId = commodityId;
        this.userEmail = userEmail;
        this.text = text;
    }
}
