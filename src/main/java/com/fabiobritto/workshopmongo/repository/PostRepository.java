package com.fabiobritto.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fabiobritto.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
