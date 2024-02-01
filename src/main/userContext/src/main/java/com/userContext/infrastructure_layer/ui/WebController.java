package com.userContext.infrastructure_layer.ui;

import com.userContext.business_logic_layer.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebController {

    @GetMapping("/employees")
    String all() {
        return "Get all Users!";
    }

    @PostMapping("/employees")
    String newUser(@RequestBody User newUser) {
        return "Create new User!";
    }



    @DeleteMapping("/employees")
    String deleteEmployee(@PathVariable Long id) {
        return "Delete User!";
    }

}
