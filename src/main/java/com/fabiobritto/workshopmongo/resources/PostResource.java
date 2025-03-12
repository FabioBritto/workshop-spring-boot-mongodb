package com.fabiobritto.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabiobritto.workshopmongo.domain.Post;
import com.fabiobritto.workshopmongo.resources.util.URL;
import com.fabiobritto.workshopmongo.services.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/springmongodb/posts", produces = {"application/json"})
@Tag(name = "springmongodb")
public class PostResource {

	@Autowired
	PostService service;
	
	@Operation(summary = "Realiza a busca de Post por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Post encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum Post encontrado com este ID"),
			@ApiResponse(responseCode = "500", description = "Erro ao procurar Post")
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
	
	@Operation(summary = "Realiza a busca de Posts por Título ou por parte dele")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Post(s) encontrado(s) com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum Post encontrado com este texto no título"),
			@ApiResponse(responseCode = "500", description = "Erro ao procurar Post")
	})
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(defaultValue = "") String text){
		System.out.println(text);
		text = URL.decodificarParam(text);
		System.out.println(text);
		List<Post> posts = service.findByTitle(text);
		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}
	
	@Operation(summary = "Realiza a busca completa de Posts utilizando como parâmetros: Texto, Data Mínima e Data Máxima")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Post(s) encontrado(s) com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum Post encontrado com este texto no título ou dentro destas datas"),
			@ApiResponse(responseCode = "500", description = "Erro ao procurar Post")
	})
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text",defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue ="") String minDate,
			@RequestParam(value="maxDate", defaultValue ="") String maxDate) {
		text = URL.decodificarParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> posts = service.fullSearch(text, min, max);
		return ResponseEntity.status(HttpStatus.OK).body(posts);
	}
	
	
}
