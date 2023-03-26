package com.willi.twitter.services;

import com.willi.twitter.controller.dto.user.UserRequestDTO;
import com.willi.twitter.model.UserModel;

import java.util.Optional;

public interface IUserService {
    UserModel saveUser(UserModel userModel);

    Optional<UserModel> getUserById(Long id);

    UserModel updateUser(UserModel user, UserRequestDTO userResquest);

    void deleteUser(Long id);
}
