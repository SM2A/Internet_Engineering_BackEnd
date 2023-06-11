package ir.ut.ece.ie.security.service.oauth2;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.security.service.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        GithubUserInfo githubUserInfo = new GithubUserInfo(oAuth2User);
        if (githubUserInfo.getEmail() == null) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }
        Optional<User> user = userRepository.findByEmail(githubUserInfo.getEmail());
        User authenticatedUser;
        authenticatedUser = user.map(value -> updateExistingUser(value, githubUserInfo))
                .orElseGet(() -> registerNewUser(githubUserInfo));
        return new UserPrincipal(authenticatedUser, oAuth2User.getAttributes());
    }

    private User registerNewUser(GithubUserInfo githubUserInfo) {
        User user = new User();
        user.setUsername(githubUserInfo.getUsername());
        user.setPassword(null);
        user.setEmail(githubUserInfo.getEmail());
        user.setBirthDate(calculateBirthDate(githubUserInfo.getCreatedAt()));
        user.setAddress(githubUserInfo.getLocation());
        return userRepository.save(user);
    }

    private User updateExistingUser(User user, GithubUserInfo githubUserInfo) {
        user.setEmail(githubUserInfo.getEmail());
        user.setAddress(githubUserInfo.getLocation());
        user.setBirthDate(calculateBirthDate(githubUserInfo.getCreatedAt()));
        return userRepository.save(user);
    }

    private String calculateBirthDate(String createdAt) {
        return LocalDate.parse(createdAt.substring(0, createdAt.indexOf("T"))).minusYears(18).toString();
    }
}
