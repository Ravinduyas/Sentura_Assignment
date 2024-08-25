package lk.ijse.Sentura_Assignment;

import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    public String createUser(String uid, String name, String email) throws IOException {
        String url = "https://c19921ec78d74a588a8a54bd5ca6a550.weavy.io" + "/api/users";

        // json body
        String jsonBody = "{"
                + "\"uid\":\"" + uid + "\","
                + "\"name\":\"" + name + "\","
                + "\"email\":\"" + email + "\""
                + "}";

        // request
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + "https://c19921ec78d74a588a8a54bd5ca6a550.weavy.io")
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();



        //executoin
        OkHttpClient client = null;
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Failed to create user: " + response.message());
            }
        }

    }
}
