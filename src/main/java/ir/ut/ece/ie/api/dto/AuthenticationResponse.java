package ir.ut.ece.ie.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private String authenticationToken;
}
