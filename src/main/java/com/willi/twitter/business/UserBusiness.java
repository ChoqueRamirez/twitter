package com.willi.twitter.business;

import com.willi.twitter.exceptions.EmailAlreadyExistExeption;
import com.willi.twitter.exceptions.UserNameAlreadyExistExeption;
import com.willi.twitter.exceptions.UserNameAndEmailAlreadyExistException;
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
        return userRepository2.existsById(user.getId());
    }

    private boolean doesTheUserNameAlreadyExist(UserModel user){
        return userRepository2.existsByName(user.getName());
    }

    private boolean doesTheEmailAlreadyExist(UserModel user){
        return userRepository2.existsByEmail(user.getEmail());
    }

    private boolean areTheCredentialsOk(UserModel user){
        boolean doesTheUserNameAlreadyExist = doesTheUserNameAlreadyExist(user);
        boolean doesTheEmailAlreadyExist = doesTheEmailAlreadyExist(user);

        if (doesTheUserNameAlreadyExist && doesTheEmailAlreadyExist){
            throw new UserNameAndEmailAlreadyExistException("El nombre y el email ya estan en uso");
        }else if (doesTheUserNameAlreadyExist){
            throw new UserNameAlreadyExistExeption("El nombre ya existe");
        }else if (doesTheEmailAlreadyExist){
            throw new EmailAlreadyExistExeption("El email ya existe");
        }else {
            return true;
        }
    }


    public boolean canTheUserBeSaved(UserModel user) {
        return areTheCredentialsOk(user);
    }


    public boolean canTheUserBeUpdated(UserModel user, UserModel userRequest){
        boolean isTryingToChangeTheName =  !userRequest.getName().equals(user.getName());
        boolean isTryingToChangeTheEmail = !userRequest.getEmail().equals(user.getEmail());

        boolean nameAndEmailOk = !doesTheUserNameAlreadyExist(userRequest) && !doesTheEmailAlreadyExist(userRequest);

        if (isTryingToChangeTheName && isTryingToChangeTheEmail) {
            if (!nameAndEmailOk) {
                throw new UserNameAndEmailAlreadyExistException("El nombre y el email ya estan en uso");
            }
        }

        if (isTryingToChangeTheName) {
            if (doesTheUserNameAlreadyExist(userRequest)) {
                throw new UserNameAlreadyExistExeption("el nombre ya esta en uso");
            }
        }

        if (isTryingToChangeTheEmail) {
            if (doesTheEmailAlreadyExist(userRequest)) {
                throw new EmailAlreadyExistExeption("El email ya esta en uso");
            }
        }


        return true;
    }



}
