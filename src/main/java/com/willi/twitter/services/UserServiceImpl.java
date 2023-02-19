package com.willi.twitter.services;

import com.willi.twitter.business.UserBusiness;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository;
    private final UserBusiness userBusiness;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserBusiness userBusiness){
        this.userRepository = userRepository;
        this.userBusiness = userBusiness;
    }

    @Override
    public Optional<UserModel> saveUser(UserModel user){
        if(!userBusiness.doesTheUserNameAlreadyExist(user)) {
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserModel> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
