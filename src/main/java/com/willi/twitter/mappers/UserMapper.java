package com.willi.twitter.mappers;

import com.willi.twitter.controller.dto.user.UserRequestDTO;
import com.willi.twitter.controller.dto.user.UserResponseDTO;
import com.willi.twitter.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toUserModel(UserRequestDTO userRequestDTO){
        return new UserModel(
                userRequestDTO.getName(),
                userRequestDTO.getEmail(),
                userRequestDTO.getPassword()
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
