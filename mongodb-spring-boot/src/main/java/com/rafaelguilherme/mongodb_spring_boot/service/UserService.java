package com.rafaelguilherme.mongodb_spring_boot.service;

import java.util.List;
import java.util.Optional;

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
	
	public User update(User obj) {
		Optional<User> optionalUser = userRepo.findById(obj.getId());

	    if (!optionalUser.isPresent()) {
	        throw new ObjectNotFoundException("Objeto não encontrado");
	    }

	    User existingUser = optionalUser.get();

	    updadteData(existingUser, obj);

	    return userRepo.save(existingUser);
	}
	
	private void updadteData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
