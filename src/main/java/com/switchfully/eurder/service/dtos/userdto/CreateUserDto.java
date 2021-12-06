package com.switchfully.eurder.service.dtos.userdto;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.domain.user.Role;

public class CreateUserDto {
    private String firstName;
    private String lastName;
    private EmailAddress emailAddress;
    private Address address;
    private String phoneNumber;
    private String password;
    private Role role;

    public CreateUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CreateUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public CreateUserDto setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public CreateUserDto setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CreateUserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public CreateUserDto setRole(Role role) {
        this.role = role;
        return this;
    }
}
