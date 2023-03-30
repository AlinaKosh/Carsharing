package com.project.carshar.repositories;

import com.project.carshar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailIgnoreCase(String email);
	User findById(long id);
}
