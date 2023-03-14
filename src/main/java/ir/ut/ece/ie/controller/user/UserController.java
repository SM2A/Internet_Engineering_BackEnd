package ir.ut.ece.ie.controller.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.user.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    public User addUser(User user) {
        return userService.addUser(user);
    }

    public User getUser(String username) {
        return userService.getUser(username).orElseThrow(() -> new OnlineShopException("User not found!"));
    }
}
