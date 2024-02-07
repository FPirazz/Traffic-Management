package com.userContext.application_layer;

import com.userContext.business_logic_layer.User;
import com.userContext.business_logic_layer.UserRepository;
import com.userContext.application_layer.exceptions.UserNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserRepository repo;
    private final UserModelAssembler assembler;

    public UserController(final UserRepository repo, final UserModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    // GET Mappings
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/users")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> user = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(user, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }
    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @GetMapping("/users/check")
    boolean one(@RequestParam String name, @RequestParam String surname, @RequestParam String password) {
        Optional<User> user = repo.findByCreds(name, surname, password);
        return user.isPresent();
    }

    // POST Mappings
    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(repo.save(newUser));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // PUT Mappings
    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        User updatedUser = repo.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setSurname(newUser.getSurname());
                    user.setRole(newUser.getRole());
                    return repo.save(user);
                })
                .orElseGet(() -> {
                   newUser.setId(id);
                   return repo.save(newUser);
                });

        EntityModel<User> entityModel = assembler.toModel(updatedUser);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // DELETE Mappings
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
