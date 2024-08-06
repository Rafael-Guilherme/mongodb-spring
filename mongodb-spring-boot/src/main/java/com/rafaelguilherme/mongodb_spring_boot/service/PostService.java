package com.rafaelguilherme.mongodb_spring_boot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelguilherme.mongodb_spring_boot.domain.Post;
import com.rafaelguilherme.mongodb_spring_boot.repository.PostRepository;
import com.rafaelguilherme.mongodb_spring_boot.service.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	public Post findById(String id) {
		return postRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return postRepo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepo.fullSearch(text, minDate, maxDate);
	}
}
