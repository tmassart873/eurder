package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Feature;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.exceptions.PasswordNotValidException;
import com.switchfully.eurder.exceptions.UserDoesNotExistException;
import com.switchfully.eurder.exceptions.UserNotAuthorizedException;
import com.switchfully.eurder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAccess(String authorization, Feature feature){
        String decodeUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodeUsernamePassword.substring(0, decodeUsernamePassword.indexOf(":"));
        String password = decodeUsernamePassword.substring(decodeUsernamePassword.indexOf(":") + 1);
        User user = userRepository.getUserByEmail(email);

        if(user == null){
            throw new UserDoesNotExistException();
        }
        if(!user.getPassword().equals(password)){
            throw new PasswordNotValidException();
        }
        if(!user.hasAccessTo(feature)){
            throw new UserNotAuthorizedException();
        }

    }

}
