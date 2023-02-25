package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }
}
