package com.willi.twitter.business;

import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {

    private final UserRepository userRepository2;

    @Autowired
    public UserBusiness(UserRepository userRepository2){
        this.userRepository2 = userRepository2;
    }

    public boolean doesUserExist(UserModel user) {
        return userRepository2.findById(user.getId()).isPresent();
    }

    public boolean doesTheUserNameAlreadyExist(UserModel user){
        return userRepository2.findByName(user.getName()) != null;
    }
}
