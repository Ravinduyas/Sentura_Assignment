package lk.ijse.Sentura_Assignment;

import lk.ijse.Sentura_Assignment.UserService;
import lk.ijse.Sentura_Assignment.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser(@RequestParam String uid, @RequestParam String name, @RequestParam(required = false) String email) throws IOException {
        return userService.createUser(uid, name, email);
    }

    @GetMapping
    public String listUsers(@RequestParam(defaultValue = "25") int take) throws IOException {
        return userService.listUsers(take);
    }

    @GetMapping("/{userId}")
    public String getUserDetails(@PathVariable String userId) throws IOException {
        return userService.getUserDetails(userId);
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId, @RequestParam String name) throws IOException {
        return userService.updateUser(userId, name);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) throws IOException {
        userService.deleteUser(userId);
    }
}
