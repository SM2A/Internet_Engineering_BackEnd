package ir.ut.ece.ie.security.service.authentication;

import ir.ut.ece.ie.api.model.authentication.AuthenticationResponse;
import ir.ut.ece.ie.api.model.authentication.LoginRequest;
import ir.ut.ece.ie.api.model.authentication.SignupRequest;

public interface AuthService {
    AuthenticationResponse login(LoginRequest request);

    void signup(SignupRequest request);
}
