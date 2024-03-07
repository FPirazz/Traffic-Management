package com.userContext.business_logic_layer;

import com.userContext.business_logic_layer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User AS u WHERE u.name = ?1 AND u.surname = ?2 AND u.password = ?3")
    Optional<User> findByCreds(final String name, final String surname, final String password);
}
