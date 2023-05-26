package ir.ut.ece.ie.controller.user;

import ir.ut.ece.ie.domain.user.User;
import ir.ut.ece.ie.exception.OnlineShopException;
import ir.ut.ece.ie.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private User loggedInUser;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestBody User user) {
        Optional<User> newUser = userService.getUser(user.getUsername());
        if (newUser.isPresent()) {
            if (!newUser.get().getPassword().equals(user.getPassword())) {
                throw new OnlineShopException("Username or password is incorrect");
            }
            loggedInUser = newUser.get();
        }
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

    @PostMapping("/signup")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.getUser(username).orElseThrow(() -> new OnlineShopException("User not found!"));
    }

    @PutMapping("{username}/credit")
    public void increaseCredit(@PathVariable String username, @RequestParam Long credit) {
        userService.increaseCredit(username, credit);
    }
}
