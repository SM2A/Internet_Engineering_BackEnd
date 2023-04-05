package ir.ut.ece.ie.domain.user;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String birthDate;
    private String address;
    private Long credit;
    private Set<Discount> usedDiscounts;

    public User(String username, String password, String email, String birthDate, String address, Long credit) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.credit = credit;
    }

    public void addUsedDiscount(Discount discount) {
        if (usedDiscounts == null) {
            usedDiscounts = new HashSet<>();
        } else {
            usedDiscounts.add(discount);
        }
    }
}
