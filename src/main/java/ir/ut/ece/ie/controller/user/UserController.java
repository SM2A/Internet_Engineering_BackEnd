package ir.ut.ece.ie.controller.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.user.UserService;

public class UserController {
    private final UserService userService;
    private User loggedInUser;

    public UserController(UserService service) {
        this.userService = service;
        this.loggedInUser = null;
    }

    public User login(User user) {
        loggedInUser = user;
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public User addUser(User user) {
        return userService.addUser(user);
    }

    public User getUser(String username) {
        return userService.getUser(username).orElseThrow(() -> new OnlineShopException("User not found!"));
    }

    public void incrementCredit(String username, Long credit) {
        User user = getUser(username);
        if (credit <= 0)
            throw new OnlineShopException("Credit must be positive value!");
        user.setCredit(user.getCredit() + credit);
    }
}
