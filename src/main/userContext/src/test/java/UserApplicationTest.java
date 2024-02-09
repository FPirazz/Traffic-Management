import com.userContext.application_layer.UserController;
import com.userContext.application_layer.UserModelAssembler;
import com.userContext.business_logic_layer.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
        UserApplicationTest.class,
        UserController.class,
        UserRepository.class
})
@AutoConfigureTestDatabase
//@AutoConfigureMockRestServiceServer
//@AutoConfigureWebTestClient
//@EnableAutoConfiguration
//@EnableTransactionManagement
//@EntityScan(basePackages="com.userContext")
@ComponentScan(basePackages = {"com.userContext"} )
public class UserApplicationTest {
    @Autowired
    private UserController controller;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserModelAssembler assembler;
    private WebTestClient client;
    public UserApplicationTest() {

    }
    @Test
    void contextLoadsTest() {
        assertThat(controller).isNotNull();
        assertThat(userRepository).isNotNull();
        this.client = MockMvcWebTestClient.bindToController(new UserController(userRepository, assembler)).build();
        assertThat(this.client).isNotNull();

        this.client.get().uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }
    @Test
    void controllerRPCTest() {
        this.client.get().uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }
}
