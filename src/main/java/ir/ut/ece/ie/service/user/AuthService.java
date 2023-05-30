package ir.ut.ece.ie.service.user;

import ir.ut.ece.ie.api.dto.AuthenticationResponse;
import ir.ut.ece.ie.api.dto.LoginRequest;
import ir.ut.ece.ie.api.dto.SignupRequest;

public interface AuthService {
    AuthenticationResponse login(LoginRequest request);

    void signup(SignupRequest request);
}
