package lk.ijse.Sentura_Assignment;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String WEAVY_SERVER_URL = "https://YOUR-WEAVY-SERVER";
    private final OkHttpClient client;

    public UserService() {
        this.client = new OkHttpClient();
    }

    // Create a user
    public String createUser(String uid, String name, String email) throws IOException {
        String url = WEAVY_SERVER_URL + "/api/users";

        String jsonBody = "{"
                + "\"uid\":\"" + uid + "\","
                + "\"name\":\"" + name + "\","
                + "\"email\":\"" + email + "\""
                + "}";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Failed to create user: " + response.message());
            }
        }
    }

    // List users
    public String listUsers(int take) throws IOException {
        String url = WEAVY_SERVER_URL + "/api/users?take=" + take;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Failed to list users: " + response.message());
            }
        }
    }

    // Get user details
    public String getUserDetails(String userId) throws IOException {
        String url = WEAVY_SERVER_URL + "/api/users/" + userId;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Failed to get user details: " + response.message());
            }
        }
    }

    // Update a user
    public String updateUser(String userId, String name) throws IOException {
        String url = WEAVY_SERVER_URL + "/api/users/" + userId;

        String jsonBody = "{"
                + "\"name\":\"" + name + "\""
                + "}";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .patch(RequestBody.create(jsonBody, MediaType.get("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Failed to update user: " + response.message());
            }
        }
    }

    // Delete a user
    public void deleteUser(String userId) throws IOException {
        String url = WEAVY_SERVER_URL + "/api/users/" + userId;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to delete user: " + response.message());
            }
        }
    }
}
