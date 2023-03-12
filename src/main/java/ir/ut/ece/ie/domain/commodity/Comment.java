package ir.ut.ece.ie.domain.commodity;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long commodityId;
    private String userEmail;
    private String text;
    private String date;
}
