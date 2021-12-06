package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final EmailAddress emailAddress;
    private final Address address;
    private final String phoneNumber;
    private Role role;

    public User(UserBuilder userBuilder){
        id = UUID.randomUUID();
        firstName = userBuilder.firstName;
        lastName = userBuilder.lastName;
        emailAddress = userBuilder.emailAddress;
        address = userBuilder.address;
        phoneNumber = userBuilder.phoneNumber;
        role = userBuilder.role;
    }


    public static final class UserBuilder {
        private UUID id;
        private String firstName;
        private String lastName;
        private EmailAddress emailAddress;
        private Address address;
        private String phoneNumber;
        private Role role;

        private UserBuilder() {
        }

        public static UserBuilder userBuilder() {
            return new UserBuilder();
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder withEmailAddress(EmailAddress emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public UserBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public UserBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
