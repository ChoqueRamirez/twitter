package com.willi.twitter.controller;

import com.willi.twitter.controller.dto.error.ErrorDetailsDTO;
import com.willi.twitter.controller.dto.user.UserCreationDTO;
import com.willi.twitter.controller.dto.user.UserResponseDTO;
import com.willi.twitter.controller.exeption.UserNameAlreadyExistExeption;
import com.willi.twitter.mappers.UserMapper;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserCreationDTO userCreation) {
        try {
            UserModel user = userMapper.toUserModel(userCreation);
            UserModel userSaved = userService.saveUser(user);
            if (userSaved != null) {
                UserResponseDTO userResponse = userMapper.toUserResponse(userSaved);
                return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (UserNameAlreadyExistExeption e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId){
        Optional<UserModel> user = userService.getUserById(userId);
        if(user.isEmpty()){
            ErrorDetailsDTO errorDetailsDTO = new ErrorDetailsDTO("User not found with ID: " + userId, HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetailsDTO);
        }
        return ResponseEntity.ok(userMapper.toUserResponse(user.get()));
    }
}
