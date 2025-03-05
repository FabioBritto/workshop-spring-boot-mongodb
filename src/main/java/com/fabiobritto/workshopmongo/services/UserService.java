package com.fabiobritto.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiobritto.workshopmongo.domain.User;
import com.fabiobritto.workshopmongo.repository.UserRepository;
import com.fabiobritto.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;

	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user =repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found"));	
	}
}
