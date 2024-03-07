import com.fasterxml.jackson.databind.ObjectMapper;
import com.userContext.application_layer.UserController;
import com.userContext.application_layer.UserModelAssembler;
import com.userContext.business_logic_layer.User;
import com.userContext.business_logic_layer.UserRepository;
import com.userContext.business_logic_layer.UserRole;
import org.hamcrest.CoreMatchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes=SpringBootUserControllerTest.class)
@ComponentScan(basePackages="com.userContext")
@AutoConfigureDataJpa
@EnableAutoConfiguration
@Import(value = {UserController.class, UserModelAssembler.class})
//@DataJpaTest
public class SpringBootUserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserModelAssembler userModelAssembler;


    @Test
    public void postTest() throws Exception {
        User userTest = new User("Test Name 1", "Test Surname 1", "Test Password 1", UserRole.Driver);
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest))
        ).andExpect(status().isCreated());

        this.deleteUserAfterTest();
    }

    @Test
    public void getOneTest() throws Exception {
        User userTest = new User("Test Name 2", "Test Surname 2", "Test Password 2", UserRole.Driver);
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest))
        ).andExpect(status().isCreated());

        MvcResult user1 = mockMvc.perform(
                get("/users/2")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andReturn();

        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Test Name 2"));
        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Test Surname 2"));
        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Test Password 2"));
        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Driver"));

        this.deleteUserAfterTest();
    }

    @Test
    public void getAllTest() throws Exception {
        User userTest1 = new User("Test Name 3", "Test Surname 3", "Test Password 3", UserRole.Driver);
        User userTest2 = new User("Test Name 4", "Test Surname 4", "Test Password 4", UserRole.Operator);

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest1))
        ).andExpect(status().isCreated());

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest2))
        ).andExpect(status().isCreated());


        MvcResult allUsers = mockMvc.perform(
                        get("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();

        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Test Name 3"));
        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Test Surname 3"));
        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Test Password 3"));
        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Driver"));

        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Test Name 4"));
        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Test Surname 4"));
        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Test Password 4"));
        assertThat(allUsers.getResponse().getContentAsString(), CoreMatchers.containsString("Operator"));

        this.deleteUserAfterTest();
    }

    @Test
    public void checkUserExistsTest() throws Exception {
        User userTest1 = new User("Test Name 5", "Test Surname 5", "Test Password 5", UserRole.Driver);
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest1))
        ).andExpect(status().isCreated());

        MvcResult userCheck = mockMvc.perform(
                        get("/users/check")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("name", "Test Name 5")
                                .param("surname", "Test Surname 5")
                                .param("password", "Test Password 5")
                ).andExpect(status().isOk()).andReturn();
        assertThat(userCheck.getResponse().getContentAsString(), CoreMatchers.containsString("true"));

        this.deleteUserAfterTest();
    }

    @Test
    public void putUserTest() throws Exception {
        User userTest1 = new User("Test Name 6", "Test Surname 6", "Test Password 6", UserRole.Driver);
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest1))
        ).andExpect(status().isCreated());

        User userTest2 = new User("Test Name 7", "Test Surname 7", "Test Password 7", UserRole.Operator);

        mockMvc.perform(
                put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest2))
        ).andExpect(status().isCreated());

        MvcResult user1 = mockMvc.perform(
                        get("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andReturn();

        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Test Name 7"));
        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Test Surname 7"));
        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Test Password 7"));
        assertThat(user1.getResponse().getContentAsString(), CoreMatchers.containsString("Operator"));

        this.deleteUserAfterTest();
    }

    @Test
    public void deleteUserTest() throws Exception {
        User userTest1 = new User("Test Name 8", "Test Surname 8", "Test Password 8", UserRole.Driver);
        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userTest1))
        ).andExpect(status().isCreated());

        mockMvc.perform(
                        delete("/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNoContent());
    }

    private void deleteUserAfterTest() throws Exception {
        mockMvc.perform(
                delete("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }
}
