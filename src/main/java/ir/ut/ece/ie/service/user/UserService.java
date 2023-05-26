package ir.ut.ece.ie.service.user;

import ir.ut.ece.ie.domain.user.User;

import java.util.Optional;

public interface UserService {
    User addUser(User user);

    Optional<User> getUser(String username);

    void increaseCredit(String username, Long credit);

}
