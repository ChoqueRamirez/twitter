package com.willi.twitter.controller;

import com.willi.twitter.controller.dto.UserCreationDTO;
import com.willi.twitter.controller.dto.UserResponseDTO;
import com.willi.twitter.controller.exeption.UserNameAlreadyExistExeption;
import com.willi.twitter.mappers.UserMapper;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            Optional<UserModel> userSaved = userService.saveUser(user);
            if (userSaved.isPresent()) {
                UserResponseDTO userResponse = userMapper.toUserResponse(userSaved.get());
                return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (UserNameAlreadyExistExeption e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
