package com.userContext.application_layer;

import com.userContext.application_layer.exceptions.UserAlreadyExistsException;
import com.userContext.business_logic_layer.DomainModel;
import com.userContext.business_logic_layer.User;

import java.util.Optional;

public class ApplicationAPIImpl implements ApplicationAPI{

    private DomainModel domainLayer;

    @Override
    public void registerUser(final String id, final String name, final String surname, final String role) throws UserAlreadyExistsException {
        Optional<User> user = domainLayer.getUserById(id);
        if(user.isEmpty()) {
            domainLayer.addNewUser(id, name, surname, role);
        } else {
            throw new UserAlreadyExistsException();
        }
    }
}
