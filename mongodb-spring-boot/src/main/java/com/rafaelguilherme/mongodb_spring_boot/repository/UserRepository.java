package com.rafaelguilherme.mongodb_spring_boot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rafaelguilherme.mongodb_spring_boot.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
