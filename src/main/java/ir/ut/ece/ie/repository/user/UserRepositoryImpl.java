package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl INSTANCE = null;
    private final Map<String, User> users = new HashMap<>();

    private UserRepositoryImpl() {

    }

    public static UserRepositoryImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserRepositoryImpl();
        return INSTANCE;
    }

    @Override
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> users) {
        users.forEach(user -> this.users.put(user.getUsername(), user));
        return users;
    }

    @Override
    public Optional<User> findById(String username) {
        return Optional.ofNullable(users.getOrDefault(username, null));
    }
}
