package com.willi.twitter.services;

import com.willi.twitter.business.UserBusiness;
import com.willi.twitter.controller.exeption.UserNameAlreadyExistExeption;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private final UserRepository userRepository1;
    private final UserBusiness userBusiness;

    @Autowired
    public UserServiceImpl(UserRepository userRepository1, UserBusiness userBusiness){
        this.userRepository1 = userRepository1;
        this.userBusiness = userBusiness;
    }

    @Override
    public UserModel saveUser(UserModel user) throws UserNameAlreadyExistExeption {
        if(!userBusiness.doesTheUserNameAlreadyExist(user)) {
            return userRepository1.save(user);
        }else {
            throw new UserNameAlreadyExistExeption("El nombre de usuario ya existe");
        }
    }

    @Override
    public Optional<UserModel> getUserById(Long id){
        return userRepository1.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository1.deleteById(id);
    }


}
