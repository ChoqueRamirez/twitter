package com.willi.twitter.controller;

import com.willi.twitter.controller.dto.error.ErrorDetailsDTO;
import com.willi.twitter.controller.dto.user.UserRequestDTO;
import com.willi.twitter.controller.dto.user.UserResponseDTO;
import com.willi.twitter.exceptions.UserNameAlreadyExistExeption;
import com.willi.twitter.exceptions.UserNotFoundException;
import com.willi.twitter.mappers.UserMapper;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userCreation) {

        UserModel user = userMapper.toUserModel(userCreation);
        UserModel userSaved = userService.saveUser(user);

        UserResponseDTO userResponse = userMapper.toUserResponse(userSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        Optional<UserModel> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        return ResponseEntity.ok(userMapper.toUserResponse(user.get()));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserRequestDTO userRequest) {
        Optional<UserModel> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        UserModel userUpdated = userService.updateUser(user.get(), userRequest);

        return ResponseEntity.ok((userMapper.toUserResponse(userUpdated)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        Optional<UserModel> user = userService.getUserById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
