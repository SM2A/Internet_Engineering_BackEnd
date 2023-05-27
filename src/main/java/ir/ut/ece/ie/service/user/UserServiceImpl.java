package ir.ut.ece.ie.service.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User addUser(User user) {
        Pattern pattern = Pattern.compile("^[A-Za-z][A-Za-z0-9]*");
        if (!pattern.matcher(user.getUsername()).matches())
            throw new OnlineShopException("Invalid username");
        return repository.save(user);
    }

    @Override
    public Optional<User> getUser(String username) {
        return repository.findById(username);
    }

    @Override
    public void increaseCredit(String username, Long credit) {
        User user = repository.findById(username)
                .orElseThrow(() -> new OnlineShopException("User not found!"));
        if (credit <= 0)
            throw new OnlineShopException("Credit must be positive value!");
        user.setCredit(user.getCredit() + credit);
        repository.save(user);
    }

}
