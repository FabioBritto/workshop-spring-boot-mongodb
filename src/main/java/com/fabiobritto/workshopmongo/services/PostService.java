package com.fabiobritto.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabiobritto.workshopmongo.domain.Post;
import com.fabiobritto.workshopmongo.repository.PostRepository;
import com.fabiobritto.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository repository;
	
	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text){
		return repository.findTitleByText(text);
		//return repository.findByTitleContainingIgonoreCase(text);
	}
}
