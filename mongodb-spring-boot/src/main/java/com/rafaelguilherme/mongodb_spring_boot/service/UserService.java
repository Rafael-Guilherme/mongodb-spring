package com.rafaelguilherme.mongodb_spring_boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelguilherme.mongodb_spring_boot.domain.User;
import com.rafaelguilherme.mongodb_spring_boot.dto.UserDTO;
import com.rafaelguilherme.mongodb_spring_boot.repository.UserRepository;
import com.rafaelguilherme.mongodb_spring_boot.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	public User findById(String id) {
		return userRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return userRepo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		userRepo.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
