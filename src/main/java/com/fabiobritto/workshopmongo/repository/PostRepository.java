package com.fabiobritto.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fabiobritto.workshopmongo.domain.Post;
import com.fabiobritto.workshopmongo.dto.CommentDTO;

public interface PostRepository extends MongoRepository<Post, String>{
	
	List<Post> findByTitleContainingIgonoreCase(String text);

	@Query("{ 'title': { $regex: ?0. $options: 'i' } }")
	List<Post> findTitleByText(String text);
	
}
