package com.userContext.infrastructure_layer.springBoot;

import com.userContext.business_logic_layer.User;
import com.userContext.infrastructure_layer.springBoot.exceptions.UserNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class WebController {

    private final UserRepository repo;
    private final UserModelAssembler assembler;

    public WebController(final UserRepository repo, final UserModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    // GET Mappings
    @GetMapping("/employees")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> user = repo.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(user, linkTo(methodOn(WebController.class).all()).withSelfRel());
    }
    @GetMapping("/employees/{id}")
    EntityModel<User> one(@PathVariable Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    // POST Mappings
    @PostMapping("/employees")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(repo.save(newUser));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    // PUT Mappings
    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {
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
    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
