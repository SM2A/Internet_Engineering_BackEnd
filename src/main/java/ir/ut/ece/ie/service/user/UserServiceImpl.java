package ir.ut.ece.ie.service.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
    private UserRepository repository;
    public UserServiceImpl() {
        this.repository = new UserRepositoryImpl();
    }
    @Override
    public User addUser(User user) {
        return repository.save(user);
    }
}
