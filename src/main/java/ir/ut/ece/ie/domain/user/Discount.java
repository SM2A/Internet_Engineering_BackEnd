package ir.ut.ece.ie.domain.user;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Discount {
    @Id
    @SerializedName(value = "code", alternate = "discountCode")
    private String code;

    @SerializedName(value = "amount", alternate = "discount")
    @Column(nullable = false)
    private Integer amount;

    @ManyToMany(mappedBy = "usedDiscounts")
    private List<User> users = new ArrayList<>();
}
