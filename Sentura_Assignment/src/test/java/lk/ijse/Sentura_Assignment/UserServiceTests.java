package lk.ijse.Sentura_Assignment;

import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTests {

    private UserService userService;
    private OkHttpClient mockClient;
    private Call mockCall;
    private Response mockResponse;

    @BeforeEach
    public void setUp() {
        mockClient = mock(OkHttpClient.class);
        mockCall = mock(Call.class);
        mockResponse = mock(Response.class);

        // Create a UserService instance using the mocked OkHttpClient
        userService = new UserService() {
            @Override
            public OkHttpClient getClient() {
                return mockClient;
            }
        };
    }

    @Test
    public void testCreateUser() throws IOException {
        String jsonResponse = "{\"id\":\"123\", \"name\":\"Kamal\"}";
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.isSuccessful()).thenReturn(true);
        when(mockResponse.body()).thenReturn(ResponseBody.create(jsonResponse, MediaType.get("application/json")));

        String response = userService.createUser("123", "Kamal", "kamal@gmail.com");

        assertEquals(jsonResponse, response);
    }

    @Test
    public void testListUsers() throws IOException {
        String jsonResponse = "[{\"id\":\"123\", \"name\":\"Kamal\"}]";
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.isSuccessful()).thenReturn(true);
        when(mockResponse.body()).thenReturn(ResponseBody.create(jsonResponse, MediaType.get("application/json")));

        String response = userService.listUsers(25);

        assertEquals(jsonResponse, response);
    }

    @Test
    public void testGetUserDetails() throws IOException {
        String jsonResponse = "{\"id\":\"123\", \"name\":\"Kamal\"}";
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.isSuccessful()).thenReturn(true);
        when(mockResponse.body()).thenReturn(ResponseBody.create(jsonResponse, MediaType.get("application/json")));

        String response = userService.getUserDetails("123");

        assertEquals(jsonResponse, response);
    }

    @Test
    public void testUpdateUser() throws IOException {
        String jsonResponse = "{\"id\":\"123\", \"name\":\"Nimal\"}";
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.isSuccessful()).thenReturn(true);
        when(mockResponse.body()).thenReturn(ResponseBody.create(jsonResponse, MediaType.get("application/json")));

        String response = userService.updateUser("123", "Jane Doe");

        assertEquals(jsonResponse, response);
    }

    @Test
    public void testDeleteUser() throws IOException {
        when(mockClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.isSuccessful()).thenReturn(true);

        userService.deleteUser("123");

        verify(mockClient).newCall(any(Request.class));
        verify(mockCall).execute();
    }
}
