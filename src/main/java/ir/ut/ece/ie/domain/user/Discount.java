package ir.ut.ece.ie.domain.user;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @SerializedName(value = "code", alternate = "discountCode")
    @Column(nullable = false, unique = true)
    private String code;

    @SerializedName(value = "amount", alternate = "discount")
    @Column(nullable = false)
    private Integer amount;
}
