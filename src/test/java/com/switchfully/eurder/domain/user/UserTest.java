package com.switchfully.eurder.domain.user;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void givenAUser_whenAllRequiredFieldsAreFilledIn_thenAssertAllTheFields() {
        //Given
        EmailAddress emailAddress = new EmailAddress("tm","order.com");
        Address address = Address.AddressBuilder.addressBuilder()
                .withStreetName("Veldstraat")
                .withStreetNumber("89")
                .withPostCode("9000")
                .withCity("Ghent")
                .build();
        User customer = User.UserBuilder.userBuilder()
                .withFirstName("Tom")
                .withLastName("Gunderland")
                .withEmailAddress(emailAddress)
                .withAddress(address)
                .withPhoneNumber("1234567890")
                .withRole(Role.CUSTOMER)
                .withPassword("tomd")
                .build();

        //When
        String actualFirstName = customer.getFirstName();
        String actualLastName = customer.getLastName();
        EmailAddress actualEmail = customer.getEmailAddress();
        Address actualAddress = customer.getAddress();
        String actualPhone = customer.getPhoneNumber();
        String actualPassword = customer.getPassword();

        EmailAddress expectedEmail = new EmailAddress("tm","order.com");
        String expectedPhoneNumber = "1234567890";
        Address expectedAddress = Address.AddressBuilder.addressBuilder()
                .withStreetName("Veldstraat")
                .withStreetNumber("89")
                .withPostCode("9000")
                .withCity("Ghent")
                .build();

        //Then
        assertEquals("Tom", actualFirstName);
        assertEquals("Gunderland", actualLastName);
        assertEquals(expectedEmail, actualEmail);
        assertEquals(expectedAddress, actualAddress);
        assertEquals("1234567890", actualPhone);
        assertEquals(Role.CUSTOMER, customer.getRole());
        assertEquals("tomd", actualPassword);
        assertNotNull(customer.getId());

    }
}