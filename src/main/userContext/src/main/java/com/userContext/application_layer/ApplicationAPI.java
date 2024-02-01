package com.userContext.application_layer;

import com.userContext.application_layer.exceptions.UserAlreadyExistsException;

public interface ApplicationAPI {

    void registerUser(final String id, final String name, final String surname, final String role) throws UserAlreadyExistsException;


}
