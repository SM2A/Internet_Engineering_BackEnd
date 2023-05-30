package ir.ut.ece.ie.service.user;

import ir.ut.ece.ie.api.dto.AuthenticationResponse;
import ir.ut.ece.ie.api.dto.LoginRequest;
import ir.ut.ece.ie.api.dto.SignupRequest;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.security.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    public AuthenticationResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthenticationResponse(request.getUsername(), jwtProvider.generateToken(authentication));
    }

    @Override
    public void signup(SignupRequest request) {

    }
}
