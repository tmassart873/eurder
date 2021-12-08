package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.Feature;
import com.switchfully.eurder.domain.user.Role;
import com.switchfully.eurder.service.SecurityService;
import com.switchfully.eurder.service.UserService;
import com.switchfully.eurder.service.dtos.userdto.CreateUserDto;
import com.switchfully.eurder.service.dtos.userdto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final Logger myLogger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping(path = "/customers",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllCustomers(@RequestHeader String authorization){
        securityService.validateAccess(authorization, Feature.CHECK_CUSTOMERS);
        return userService.getAllCustomers();
    }
    @GetMapping(path = "/customers/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getSingleCustomer(@PathVariable String id, @RequestHeader String authorization){
        securityService.validateAccess(authorization, Feature.CHECK_SINGLE_CUSTOMER);
        return userService.getCustomerById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createNewUser(@RequestBody CreateUserDto createUserDto, @RequestHeader(required = false) String authorization){
        // myLogger.debug("Creation of a new admin");
        userService.validateEmailAddress(createUserDto);
        if(createUserDto.getRole().equals(Role.CUSTOMER)){
            return userService.addNewCustomer(createUserDto);
        }
        securityService.validateAccess(authorization, Feature.REGISTER_ADMIN);
        return userService.addNewAdmin(createUserDto);
    }
}
