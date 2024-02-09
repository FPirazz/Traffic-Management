import com.userContext.business_logic_layer.User;
import com.userContext.business_logic_layer.UserRepository;
import com.userContext.business_logic_layer.UserRole;
import com.userContext.infrastructure_layer.springBoot.UserApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {UserApplication.class})
@ComponentScan(basePackages = {"com.userContext"} )
public class SpringBootEntityIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveAndCheckRepoData() {
        User user = userRepository.save(new User("Test Name", "Test Surname", "Test Password", UserRole.Operator));
        Optional<User> searchedUser = userRepository.findById(user.getId());

        assertTrue(searchedUser.isPresent());
        assertEquals(user.getId(), searchedUser.get().getId());
        assertEquals(user.getName(), searchedUser.get().getName());
        assertEquals(user.getSurname(), searchedUser.get().getSurname());
        assertEquals(user.getPassword(), searchedUser.get().getPassword());
        assertEquals(user.getRole(), searchedUser.get().getRole());
    }
}
