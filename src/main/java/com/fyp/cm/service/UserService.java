package com.fyp.cm.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fyp.cm.model.User;
@Service
public interface UserService {
    List<User> getAllUsers();
    User addUser(User device);
    User updateUser(String id, User device);
    boolean deleteUser(String id);
    User getUserById(String id);
}
