package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;

import java.util.UUID;

public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private EmailAddress emailAddress;
    private Address address;
    private String phoneNumber;
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
            User user = new User(this);
            user.address = this.address;
            user.lastName = this.lastName;
            user.role = this.role;
            user.firstName = this.firstName;
            user.id = this.id;
            user.emailAddress = this.emailAddress;
            user.phoneNumber = this.phoneNumber;
            return user;
        }
    }
}
