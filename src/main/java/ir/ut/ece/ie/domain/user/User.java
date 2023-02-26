package ir.ut.ece.ie.domain.user;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String email;
    private String birthDate;
    private String address;
    private Long credit;
}