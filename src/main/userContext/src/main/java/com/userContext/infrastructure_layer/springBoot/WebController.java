package com.userContext.infrastructure_layer.springBoot;

import com.userContext.business_logic_layer.User;
import com.userContext.infrastructure_layer.springBoot.exceptions.UserNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WebController {

    private final UserRepository repo;

    public WebController(final UserRepository repo) {
        this.repo = repo;
    }

    // GET Mappings
    @GetMapping("/employees")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> user = repo.findAll().stream()
                .map(userList -> EntityModel.of(userList,
                        linkTo(methodOn(WebController.class).one(userList.getId())).withSelfRel(),
                        linkTo(methodOn(WebController.class).all()).withRel("employees")
                        ))
                .collect(Collectors.toList());

        return CollectionModel.of(user, linkTo(methodOn(WebController.class).all()).withSelfRel());
    }
    @GetMapping("/employees/{id}")
    EntityModel<User> one(@PathVariable Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return EntityModel.of(user,
                linkTo(methodOn(WebController.class).one(id)).withSelfRel(),
                linkTo(methodOn(WebController.class).all()).withRel("employees")
                );
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
