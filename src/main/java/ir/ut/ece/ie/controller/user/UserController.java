package ir.ut.ece.ie.controller.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private User loggedInUser;

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        loggedInUser = user;
        return loggedInUser;
    }

    @GetMapping("/logout")
    public void logout() {
        loggedInUser = null;
    }

    @GetMapping("/loggedInUser")
    public User getLoggedInUser() {
        return loggedInUser;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username).orElseThrow(() -> new OnlineShopException("User not found!"));
    }

    @PutMapping("{username}/credit")
    public void incrementCredit(@RequestParam String username, @RequestParam Long credit) {
        User user = getUser(username);
        if (credit <= 0)
            throw new OnlineShopException("Credit must be positive value!");
        user.setCredit(user.getCredit() + credit);
    }
}
