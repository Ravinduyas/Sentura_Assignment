package lk.ijse.Sentura_Assignment;

import lk.ijse.Sentura_Assignment.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        String jsonResponse = "{\"id\":\"123\", \"name\":\"Kamal\"}";
        given(userService.createUser("123", "Kamal", "Kamal")).willReturn(jsonResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .param("uid", "123")
                        .param("name", "Kamal")
                        .param("email", "kamal@gmail.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void testListUsers() throws Exception {
        String jsonResponse = "[{\"id\":\"123\", \"name\":\"Nimal\"}]";
        given(userService.listUsers(25)).willReturn(jsonResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                        .param("take", "25")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void testGetUserDetails() throws Exception {
        String jsonResponse = "{\"id\":\"123\", \"name\":\"Kamal\"}";
        given(userService.getUserDetails("123")).willReturn(jsonResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void testUpdateUser() throws Exception {
        String jsonResponse = "{\"id\":\"123\", \"name\":\"Kamal\"}";
        given(userService.updateUser("123", "Kamal")).willReturn(jsonResponse);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/users/123")
                        .param("name", "Nimal")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResponse));
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/123"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
