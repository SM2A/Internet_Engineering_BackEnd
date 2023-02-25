package ir.ut.ece.ie.service.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.user.UserRepository;
import ir.ut.ece.ie.repository.user.UserRepositoryImpl;

import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl() {
        this.repository = new UserRepositoryImpl();
    }

    @Override
    public User addUser(User user) {
        Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z0-9]*");
        if (!pattern.matcher(user.getUsername()).matches())
            throw new OnlineShopException("Invalid username");
        return repository.save(user);
    }
}
