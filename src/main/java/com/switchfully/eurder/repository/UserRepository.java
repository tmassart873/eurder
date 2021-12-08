package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.domain.user.Role;
import com.switchfully.eurder.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository {
    private HashMap<UUID, User> users;

    public UserRepository(UserDataFactory userDataFactory) {
        this.users = userDataFactory.getDefaultUsers();
    }

    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmailAddress().toString().equals(email))
                .findFirst().orElse(null);
    }

    public List<User> getAllUsers() {
        return users.values().stream().toList();
    }

    public List<EmailAddress> getAllEmailAddress() {
        return users.values().stream()
                .map(User::getEmailAddress)
                .toList();
    }
    public User getCustomerById(String id) {
        return users.values().stream()
                .filter(user -> user.getRole().equals(Role.CUSTOMER))
                .filter(user -> user.getId().toString().equals(id))
                .findFirst().orElse(null);
    }
}
