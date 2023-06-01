package ir.ut.ece.ie.domain.user;

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
@Table(name = "Users")
public class User {
    @Id
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String birthDate;

    @Column
    private String address;

    @Column
    private Long credit;

    @Column
    private String currentDiscountCode;

    @ManyToMany
    @JoinTable(
            name = "used_discounts",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "code"))
    private List<Discount> usedDiscounts = new ArrayList<>();
}


