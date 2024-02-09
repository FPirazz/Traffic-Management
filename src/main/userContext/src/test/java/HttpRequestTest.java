import com.userContext.application_layer.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {HttpRequestTest.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = {"com.userContext"} )
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test() {
        this.restTemplate.getForObject("http://localhost:" + port + "/users", CollectionModel.class);
    }

}
