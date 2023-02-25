package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String username) {
        return Optional.ofNullable(users.getOrDefault(username, null));
    }
}
