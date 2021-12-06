package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.domain.Feature;
import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.repository.UserRepository;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final EmailAddress emailAddress;
    private final Address address;
    private final String phoneNumber;
    private String password;
    private Role role;

    public User(UserBuilder userBuilder){
        validateUserInput(userBuilder);
        id = UUID.randomUUID();
        firstName = userBuilder.firstName;
        lastName = userBuilder.lastName;
        emailAddress = userBuilder.emailAddress;
        address = userBuilder.address;
        phoneNumber = userBuilder.phoneNumber;
        password = userBuilder.password;
        role = userBuilder.role;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public static final class UserBuilder {
        private String firstName;
        private String lastName;
        private EmailAddress emailAddress;
        private Address address;
        private String phoneNumber;
        private String password;
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

        public UserBuilder withPassword(String password){
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
    public boolean hasAccessTo(Feature feature) {
        return this.role.containsFeature(feature);
    }

    public boolean validateUserInput(UserBuilder builder){
        String firstName = builder.firstName;
        String lastName = builder.lastName;
        EmailAddress emailAddress = builder.emailAddress;
        Address address = builder.address;
        String phoneNumber = builder.phoneNumber;
        if (firstName == null || firstName.trim().equals("")) {
            throw new IllegalArgumentException("The first name should be filled!");
        }
        if (lastName == null || lastName.trim().equals("")) {
            throw new IllegalArgumentException("The last name should be filled!");
        }
        if (address == null) {
            throw new IllegalArgumentException("The address should be filled!");
        }
        if (address.getCity() == null || address.getCity().trim().equals("")) {
            throw new IllegalArgumentException("The city should be filled!");
        }
        if (address.getPostCode() == null || address.getPostCode().trim().equals("")) {
            throw new IllegalArgumentException("The postcode should be filled!");
        }
        if (address.getStreetName() == null || address.getStreetName().trim().equals("")) {
            throw new IllegalArgumentException("The street name should be filled!");
        }
        if (address.getStreetNumber() == null || address.getStreetNumber().trim().equals("")) {
            throw new IllegalArgumentException("The street number should be filled!");
        }
        if (phoneNumber== null || phoneNumber.trim().equals("") ) {
            throw new IllegalArgumentException("The street number should be filled!");
        }


        return true;
    }
}
