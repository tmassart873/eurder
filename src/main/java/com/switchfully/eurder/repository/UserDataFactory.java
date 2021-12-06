package com.switchfully.eurder.repository;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.domain.user.Role;
import com.switchfully.eurder.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class UserDataFactory {
    public HashMap<UUID, User> getDefaultUsers(){
        User admin = User.UserBuilder.userBuilder()
                .withFirstName("T.")
                .withLastName("M.")
                .withPassword("admin_tm")
                .withAddress(Address.AddressBuilder.addressBuilder()
                        .withCity("Ghent")
                        .withPostCode("9000")
                        .withStreetName("Paleisstraat")
                        .withStreetNumber("89")
                        .build())
                .withEmailAddress(new EmailAddress("admin.tm", "order.com"))
                .withPhoneNumber("55142514253")
                .withRole(Role.ADMIN)
                .build();
        HashMap<UUID, User> defaultUsers = new HashMap<>();
        defaultUsers.put(admin.getId(), admin);
        return defaultUsers;
    }
}
