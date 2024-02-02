package com.userContext.infrastructure_layer.springBoot;

import com.userContext.business_logic_layer.User;
import com.userContext.infrastructure_layer.springBoot.exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {

    private final UserRepository repo;

    public WebController(final UserRepository repo) {
        this.repo = repo;
    }

    // GET Mappings
    @GetMapping("/employees")
    List<User> all() {
        return repo.findAll();
    }
    @GetMapping("/employees/{id}")
    User one(@PathVariable Long id) {

        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // POST Mappings
    @PostMapping("/employees")
    User newUser(@RequestBody User newUser) {
        return repo.save(newUser);
    }

    // PUT Mappings
    @PutMapping("/employees/{id}")
    User replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {

        return repo.findById(id)
                .map(employee -> {
                    employee.setName(newUser.getName());
                    employee.setRole(newUser.getRole());
                    return repo.save(employee);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repo.save(newUser);
                });
    }

    // DELETE Mappings
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repo.deleteById(id);
    }

}
