package com.userContext.business_logic_layer;

import java.util.Optional;

public interface DomainModel {

    void addNewUser(final String name, final String surname);
    Optional<User> getUserById(String userId);
    Optional<User> getUserByName(String userName, String userSurname);
    Optional<User> getUserByRole(String userRole);


}
