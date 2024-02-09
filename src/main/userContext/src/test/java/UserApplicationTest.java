import com.userContext.application_layer.UserController;
import com.userContext.business_logic_layer.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserApplicationTest.class)
@AutoConfigureTestDatabase
@ComponentScan(basePackages = {"com.userContext"} )
public class UserApplicationTest {
    @Autowired
    private UserController controller;
    @Autowired
    private UserRepository userRepository;
    @Test
    void contextLoadsTest() {
        assertThat(controller).isNotNull();
        assertThat(userRepository).isNotNull();
    }
}
