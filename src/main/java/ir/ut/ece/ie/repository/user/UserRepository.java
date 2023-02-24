package ir.ut.ece.ie.repository.user;

import ir.ut.ece.ie.domain.user.User;

public interface UserRepository {
    User save(User user);
}
