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
        this.checkSavedData(user, searchedUser.get());
        this.checkSetData(searchedUser.get());
    }

    private void checkSetData(User searchedUser) {
        searchedUser.setId(5L);
        assertEquals(searchedUser.getId(), 5L);

        searchedUser.setName("New Name");
        assertEquals(searchedUser.getName(), "New Name");

        searchedUser.setSurname("New Surname");
        assertEquals(searchedUser.getSurname(), "New Surname");

        searchedUser.setPassword("New Password");
        assertEquals(searchedUser.getPassword(), "New Password");

        searchedUser.setRole(UserRole.Operator);
        assertEquals(searchedUser.getRole(), UserRole.Operator);
    }

    private void checkSavedData(final User user, final User searchedUser) {
        assertEquals(user.getId(), searchedUser.getId());
        assertEquals(user.getName(), searchedUser.getName());
        assertEquals(user.getSurname(), searchedUser.getSurname());
        assertEquals(user.getPassword(), searchedUser.getPassword());
        assertEquals(user.getRole(), searchedUser.getRole());
    }
}
