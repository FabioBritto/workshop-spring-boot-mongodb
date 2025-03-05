package com.fabiobritto.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fabiobritto.workshopmongo.domain.Post;
import com.fabiobritto.workshopmongo.dto.CommentDTO;

public interface PostRepository extends MongoRepository<Post, String>{
	
	public List<Post> findByTitleContainingIgonoreCase(String text);
}
