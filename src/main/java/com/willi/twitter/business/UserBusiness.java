package com.willi.twitter.business;

import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {
    @Autowired
    private UserRepository userRepository;

    public boolean doesUserExist(UserModel user) {
        return userRepository.findById(user.getId()).isPresent();
    }

    public boolean doesTheUserNameAlreadyExist(UserModel user){
        if(userRepository.findByName(user.getName()).isPresent()){
            return true;
        };
        return false;
    }
}
