package ir.ut.ece.ie.controller.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.service.user.UserService;
import ir.ut.ece.ie.service.user.UserServiceImpl;

public class UserController {
    private UserService userService;
    public UserController(UserService service) {
        this.userService = service;
    }
    public User addUser(User user) {
        return userService.addUser(user);
    }
}
