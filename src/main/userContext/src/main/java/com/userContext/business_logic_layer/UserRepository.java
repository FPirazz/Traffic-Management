package com.userContext.business_logic_layer;

import com.userContext.business_logic_layer.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
