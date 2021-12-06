package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.domain.user.Role;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.dtos.userdto.CreateUserDto;
import com.switchfully.eurder.service.dtos.userdto.UserDto;
import com.switchfully.eurder.service.mappers.UserMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }


    public UserDto addNewCustomer(CreateUserDto createUserDto) {
        User user = userMapper.mapCreateUserDtoToUser(createUserDto);
        if (!createUserDto.getRole().equals(Role.CUSTOMER)) {
            throw new IllegalArgumentException("You can only register as a customer.");
        }
        userRepository.save(user);
        return userMapper.mapUserToUserDto(user);

    }

    public UserDto addNewAdmin(CreateUserDto createUserDto) {
        User user = userMapper.mapCreateUserDtoToUser(createUserDto);
        if (!createUserDto.getRole().equals(Role.ADMIN)) {
            throw new IllegalArgumentException("You can only register as an admin.");
        }
        userRepository.save(user);
        return userMapper.mapUserToUserDto(user);
    }

    public List<UserDto> getAllCustomers() {
        return getAllUsers().stream().filter(user -> user.getRole().equals(Role.CUSTOMER)).toList();
    }

    public boolean validateEmailAddress(CreateUserDto user){
        EmailAddress emailAddressToValidate = user.getEmailAddress();
        System.out.println(emailAddressToValidate);
        System.out.println(userRepository.getAllEmailAddress());
        System.out.println(userRepository.getAllEmailAddress().contains(emailAddressToValidate));
        if (userRepository.getAllEmailAddress().contains(emailAddressToValidate)) {
            throw new IllegalArgumentException("The email address already exists!");
        }
        return true;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers().stream().map(userMapper::mapUserToUserDto).toList();
    }
}
