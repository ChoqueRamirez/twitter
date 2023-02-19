package com.willi.twitter.services;

import com.willi.twitter.model.UserModel;

import java.util.Optional;

public interface IUserService {
    Optional<UserModel> saveUser(UserModel userModel);

    Optional<UserModel> getUserById(Long id);

    void deleteUser(Long id);
}
