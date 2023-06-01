package ir.ut.ece.ie.security.service.oauth2;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@AllArgsConstructor
public class GithubUserInfo {
    protected OAuth2User oAuth2User;

    public String getUsername() {
        return oAuth2User.getAttribute("login");
    }

    public String getName() {
        return oAuth2User.getAttribute("name");
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }

    public String getLocation() {
        return oAuth2User.getAttribute("location");
    }

    public String getCreatedAt() {
        return oAuth2User.getAttribute("created_at");
    }
}
