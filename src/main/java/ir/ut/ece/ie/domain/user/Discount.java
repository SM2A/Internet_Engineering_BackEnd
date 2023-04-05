package ir.ut.ece.ie.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount {
    private String code;
    private Integer amount;
}
