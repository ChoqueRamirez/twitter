package com.willi.twitter.mappers;

import com.willi.twitter.controller.dto.UserCreationDTO;
import com.willi.twitter.controller.dto.UserResponseDTO;
import com.willi.twitter.model.UserModel;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toUserModel(UserCreationDTO userCreationDTO){
        return new UserModel(
                userCreationDTO.getName(),
                userCreationDTO.getEmail(),
                userCreationDTO.getPassword()
        );
    }

    public UserResponseDTO toUserResponse(UserModel user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }


}
