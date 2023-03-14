package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Iterable<User> saveAll(Iterable<User> users);

    Optional<User> findById(String username);
}
