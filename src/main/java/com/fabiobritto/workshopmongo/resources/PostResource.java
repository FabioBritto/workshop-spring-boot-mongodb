package com.fabiobritto.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabiobritto.workshopmongo.domain.Post;
import com.fabiobritto.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	PostService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = service.findById(id);
		//Por algum motivo, o ID estava indo como null. Por isso, setei manualmente
		//post.getAuthor().setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
}
