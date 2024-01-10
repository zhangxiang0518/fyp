package com.fyp.cm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.cm.exception.ResourceNotFoundException;
import com.fyp.cm.model.User;
import com.fyp.cm.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addUser(User user) {
        user.setIsActive(Boolean.FALSE);
        return userRepo.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            user.setId(existingUser.getId());
            user.setCreatedDate(existingUser.getCreatedDate());
            return userRepo.save(user);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(String id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(String id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
    public List<User> findAllUnActiveUsers() {
        return userRepo.findAllByIsActiveAndIsAdmin(false, false);
    }
}