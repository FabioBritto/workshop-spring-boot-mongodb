package com.fabiobritto.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fabiobritto.workshopmongo.domain.Post;
import com.fabiobritto.workshopmongo.domain.User;
import com.fabiobritto.workshopmongo.dto.UserDTO;
import com.fabiobritto.workshopmongo.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "springmongodb/users", produces = {"application/json"})
@Tag(name = "springmongodb")
public class UserResource {
	
	@Autowired
	private UserService service;

	@Operation(summary = "Realiza a busca de todos os Usuários")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso"),
		@ApiResponse(responseCode = "404", description = "Não encontrado nenhum Usuário no sistema"),
		@ApiResponse(responseCode = "500", description = "Erro ao procurar Usuários")
	})
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> users = service.findAll();
		List<UserDTO> dtos = users.stream().map(o -> new UserDTO(o)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);		
	}
	
	@Operation(summary = "Realiza a busca de Usuário por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Nenhum Usuário encontrado com este ID"),
		@ApiResponse(responseCode = "500", description = "Erro ao procurar Usuário")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = service.findById(id);
		UserDTO dto = new UserDTO(user);
		return ResponseEntity.ok().body(dto);
	}
	
	@Operation(summary = "Realiza a inserção de Usuários")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos"),
		@ApiResponse(responseCode = "500", description = "Erro ao cadastrar Usuário")
	})
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO dto){
		User user = service.fromDTO(dto);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Realiza a deleção de Usuário por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Dados inválidos"),
			@ApiResponse(responseCode = "404", description = "Não encontrado nenhum Usuário no sistema com este ID"),
			@ApiResponse(responseCode = "500", description = "Erro ao deletar Usuário")
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@Operation(summary = "Realiza a atualização de dados de Usuário por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos"),
		@ApiResponse(responseCode = "404", description = "Não encontrado nenhum Usuário no sistema com este ID"),
		@ApiResponse(responseCode = "500", description = "Erro ao atualizar Usuário")
	})
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@RequestBody UserDTO dto, @PathVariable String id){
		User user = service.fromDTO(dto);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@Operation(summary = "Realiza a busca de todos os Posts de um Usuário por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Posts encontrados com sucesso"),
		@ApiResponse(responseCode = "404", description = "Não encontrado nenhum Usuário no sistema com este ID"),
		@ApiResponse(responseCode = "400", description = "Dados inválidos"),
		@ApiResponse(responseCode = "500", description = "Erro ao procurar Posts")
	})
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
		List<Post> posts = service.findById(id).getPosts();	
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(posts);
	}
}
