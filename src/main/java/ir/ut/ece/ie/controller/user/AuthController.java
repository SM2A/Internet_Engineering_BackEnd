package ir.ut.ece.ie.controller.user;

import ir.ut.ece.ie.api.model.authentication.AuthenticationResponse;
import ir.ut.ece.ie.api.model.authentication.LoginRequest;
import ir.ut.ece.ie.api.model.authentication.SignupRequest;
import ir.ut.ece.ie.security.service.authentication.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/api/auth/login")
    public AuthenticationResponse authenticate(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/redirect")
    public void oauthToken(@RequestParam String token) {
        System.out.println(token);
    }
}