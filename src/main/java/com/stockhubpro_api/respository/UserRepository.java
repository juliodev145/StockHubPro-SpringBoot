package com.stockhubpro_api.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockhubpro_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
