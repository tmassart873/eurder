package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.service.dtos.userdto.CreateUserDto;
import com.switchfully.eurder.service.dtos.userdto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapCreateUserDtoToUser(CreateUserDto createUserDto) {
        return User.UserBuilder.userBuilder()

                .withFirstName(createUserDto.getFirstName())
                .withLastName(createUserDto.getLastName())
                .withEmailAddress(createUserDto.getEmailAddress())
                .withAddress(createUserDto.getAddress())
                .withPhoneNumber(createUserDto.getPhoneNumber())
                .withPassword(createUserDto.getPassword())
                .withRole(createUserDto.getRole())
                .build();
    }

    public UserDto mapUserToUserDto(User user) {

        return new UserDto()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmailAddress(user.getEmailAddress())
                .setAddress(user.getAddress())
                .setPhoneNumber(user.getPhoneNumber())
                .setPassword(user.getPassword())
                .setRole(user.getRole());
    }
}
