package com.userContext.business_logic_layer;

import com.userContext.business_logic_layer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT * FROM USER AS u WHERE u.name = ?1 AND u.surname = ?2 AND u.password = ?3")
//    boolean findRegisteredUser(final String name, final String surname, final String password);
}
