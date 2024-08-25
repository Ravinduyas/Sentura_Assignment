package lk.ijse.Sentura_Assignment;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
