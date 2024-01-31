package com.userContext.business_logic_layer;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class DomainModelImpl implements DomainModel{

    private Map<String, User> users;
    static Logger logger = Logger.getLogger("[DomainModel]");
    @Override
    public void addNewUser(String name, String surname) {

    }

    @Override
    public Optional<User> getUserById(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByName(String userName, String userSurname) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByRole(String userRole) {
        return Optional.empty();
    }
}
