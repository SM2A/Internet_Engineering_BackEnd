package ir.ut.ece.ie.domain.buylist;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Discount {
    @SerializedName(value = "code", alternate = "discountCode")
    private String code;
    @SerializedName(value = "amount", alternate = "discount")
    private Integer amount;
}
