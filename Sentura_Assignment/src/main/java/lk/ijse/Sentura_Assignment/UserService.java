package lk.ijse.Sentura_Assignment;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Service
public class UserService {
    private static final String API_KEY = "wys_rFR6AgDQfqrvOWjS04qNiJgkgDEbMZ4JhS1w";
    private static final String WEAVY_SERVER_URL = "https://c19921ec78d74a588a8a54bd5ca6a550.weavy.io";
    private final OkHttpClient client;

    @Autowired
    private UserService userService;

    public UserService() {
        this.client = new OkHttpClient();
    }
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
}
