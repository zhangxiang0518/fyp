package com.fyp.cm.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fyp.cm.model.User;
@Repository
public interface UserRepo extends MongoRepository<User, String> {
     List<User> findAllByIsActiveAndIsAdmin(boolean isActive, boolean isAdmin);
}
