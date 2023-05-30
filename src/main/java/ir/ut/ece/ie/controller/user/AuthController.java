package ir.ut.ece.ie.controller.user;

import ir.ut.ece.ie.api.model.authentication.AuthenticationResponse;
import ir.ut.ece.ie.api.model.authentication.LoginRequest;
import ir.ut.ece.ie.api.model.authentication.SignupRequest;
import ir.ut.ece.ie.security.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthenticationResponse authenticate(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
