package com.fabiobritto.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabiobritto.workshopmongo.domain.User;
import com.fabiobritto.workshopmongo.dto.UserDTO;
import com.fabiobritto.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> users = service.findAll();
		List<UserDTO> dtos = users.stream().map(o -> new UserDTO(o)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);		
	}
}
