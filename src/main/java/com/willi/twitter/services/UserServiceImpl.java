package com.willi.twitter.services;

import com.willi.twitter.business.UserBusiness;
import com.willi.twitter.controller.dto.user.UserRequestDTO;
import com.willi.twitter.exceptions.UserNameAlreadyExistExeption;
import com.willi.twitter.mappers.UserMapper;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository1;
    private final UserBusiness userBusiness;
    private final UserMapper userMapper1;

    @Autowired
    public UserServiceImpl(UserRepository userRepository1, UserBusiness userBusiness, UserMapper userMapper1){
        this.userRepository1 = userRepository1;
        this.userBusiness = userBusiness;
        this.userMapper1 = userMapper1;
    }

    @Override
    public UserModel saveUser(UserModel user) {
        if(userBusiness.canTheUserBeSaved(user)) {
            return userRepository1.save(user);
        }
        return null;
    }

    @Override
    public Optional<UserModel> getUserById(Long id){
        return userRepository1.findById(id);
    }

    @Override
    public UserModel updateUser(UserModel originalUser, UserRequestDTO userResquest) {
        UserModel userRequest = userMapper1.toUserModel(userResquest);

        boolean canTheUserBeUpdated = userBusiness.canTheUserBeUpdated(originalUser, userRequest);
        if(canTheUserBeUpdated){
            originalUser.setName(userRequest.getName());
            originalUser.setEmail(userRequest.getEmail());
            originalUser.setPassword(userRequest.getPassword());

            return userRepository1.save(originalUser);
        }else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository1.deleteById(id);
    }


}
