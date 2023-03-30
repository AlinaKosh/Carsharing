package com.project.carshar.services;

import com.project.carshar.model.Role;
import com.project.carshar.model.User;
import com.project.carshar.repositories.RoleRepository;
import com.project.carshar.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRep;
	private final RoleRepository roleRepository;


	public Iterable<User> findAll() {
		return userRep.findAll();
	}

	public User findById(long id) {
		return userRep.findById(id);
	}


	public void delete(User user) {
		userRep.delete(user);
	}

	public User findByEmail(String email) {
		return userRep.findByEmailIgnoreCase(email);
	}

	@Transactional
	public void deleteById(long id) {
		userRep.deleteById(id);
	}

	@Transactional
	public void create(User user) throws Exception{
		if (emailExist(user.getEmail()))
			throw new Exception("Данный почтовый адрес занят");
		user.setActive(1);
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRep.save(user);
	}

	@Transactional
	public void update(User user) throws Exception{
		userRep.save(user);
	}

	private boolean emailExist(String email) {
		User user = userRep.findByEmailIgnoreCase(email);
		if (user != null) {
			return true;
		}
		return false;
	}
}
