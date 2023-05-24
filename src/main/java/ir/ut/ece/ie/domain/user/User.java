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

    @Column(nullable = false)
    private String password;

    @Column
    private String birthDate;

    @Column
    private String address;

    @Column
    private Long credit;

    @OneToMany
    private List<Discount> usedDiscounts = new ArrayList<>();
}


