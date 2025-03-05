package com.fabiobritto.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiobritto.workshopmongo.domain.User;
import com.fabiobritto.workshopmongo.dto.UserDTO;
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
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(),dto.getName(),dto.getName());	
	}
	
	public void delete(String id) {
		//Aqui, aproveito o findById apenas pra lançar a exceção se necessário
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = findById(user.getId());
		updateData(newUser, user);
		return repository.save(newUser);
	}
	
	public void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}
	
}
