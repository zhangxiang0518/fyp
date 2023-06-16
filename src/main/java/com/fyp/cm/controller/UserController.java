package com.fyp.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyp.cm.model.User;
import com.fyp.cm.response.CustomResponse;
import com.fyp.cm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        CustomResponse<List<User>> response = new CustomResponse<>(HttpStatus.OK.value(), "Success", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unactive")
    public ResponseEntity<CustomResponse<List<User>>> findAllUnActiveUsers() {
        List<User> users = userService.findAllUnActiveUsers();
        CustomResponse<List<User>> response = new CustomResponse<>(HttpStatus.OK.value(),
                "Success", users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        if (user != null) {
            CustomResponse<User> response = new CustomResponse<>(HttpStatus.OK.value(), "Success", user);
            return ResponseEntity.ok(response);
        } else {
            CustomResponse<User> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "User not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<User>> addUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        CustomResponse<User> response = new CustomResponse<>(HttpStatus.CREATED.value(), "User added", savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<User>> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            CustomResponse<User> response = new CustomResponse<>(HttpStatus.OK.value(), "User updated", updatedUser);
            return ResponseEntity.ok(response);
        } else {
            CustomResponse<User> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "User not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            CustomResponse<String> response = new CustomResponse<>(HttpStatus.OK.value(), "User deleted", id);
            return ResponseEntity.ok(response);
        } else {
            CustomResponse<String> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "User not found",
                    null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
