package ir.ut.ece.ie.security.service.authentication;

import ir.ut.ece.ie.api.model.authentication.AuthenticationResponse;
import ir.ut.ece.ie.api.model.authentication.LoginRequest;
import ir.ut.ece.ie.api.model.authentication.SignupRequest;
import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
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
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new OnlineShopException("username already used.");
        } else if (userRepository.existsByEmail(request.getEmail())) {
            throw new OnlineShopException("email already used.");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setBirthDate(request.getBirthDate());
        user.setAddress(request.getAddress());
        userRepository.save(user);
    }
}

