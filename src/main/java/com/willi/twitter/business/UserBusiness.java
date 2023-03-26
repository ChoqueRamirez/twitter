package com.willi.twitter.business;

import com.willi.twitter.controller.dto.user.UserRequestDTO;
import com.willi.twitter.controller.exeption.EmailAlreadyExistExeption;
import com.willi.twitter.controller.exeption.UserNameAlreadyExistExeption;
import com.willi.twitter.model.UserModel;
import com.willi.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean doesTheEmailAlreadyExist(UserModel user){
        return userRepository2.findByEmail(user.getEmail()) != null;
    }

    public boolean canTheUserBeSaved(UserModel user){
        if (doesTheUserNameAlreadyExist(user)){
            throw new UserNameAlreadyExistExeption("El nombre de usuario ya existe");
        } else if (doesTheEmailAlreadyExist(user)) {
            throw new EmailAlreadyExistExeption("El email ya existe");
        }
        return true;
    }

    public boolean canTheUserBeUpdated(UserModel user, UserModel userResquest){
        boolean isTryingToChangeTheName =  !userResquest.getName().equals(user.getName());
        boolean isTryingToChangeTheEmail = !userResquest.getEmail().equals(user.getEmail());
        boolean isTryingToChangeThePassword = !userResquest.getPassword().equals(user.getPassword());

        if (isTryingToChangeTheName && isTryingToChangeTheEmail){
            return !doesTheUserNameAlreadyExist(userResquest) && !doesTheEmailAlreadyExist(userResquest);
        }else if(isTryingToChangeTheName){
            return !doesTheUserNameAlreadyExist(userResquest);
        } else if (isTryingToChangeTheEmail) {
            return !doesTheEmailAlreadyExist(userResquest);
        }
        return isTryingToChangeThePassword;
    }

}
