package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.domain.user.Role;
import com.switchfully.eurder.service.dtos.userdto.CreateUserDto;
import com.switchfully.eurder.service.dtos.userdto.UserDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerIntegrationTest {

    @Value("${server.port}")
    private int port;

    @Test
    void registerANewUser_whenPostANewUser_thenCreateUserInUserRepository() {
        //Given
        Address address = Address.AddressBuilder.addressBuilder()
                .withStreetName("Veldstraat")
                .withStreetNumber("68")
                .withPostCode("9000")
                .withCity("Ghent")
                .build();
        EmailAddress emailAddress = new EmailAddress("tm", "order.com");
        CreateUserDto createUserDto = new CreateUserDto()
                .setAddress(address)
                .setEmailAddress(emailAddress)
                .setFirstName("T")
                .setLastName("M")
                .setRole(Role.CUSTOMER)
                .setPassword("tm")
                .setPhoneNumber("789641230");

        //When
        UserDto userDto = RestAssured
                .given()
                .body(createUserDto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .post("/users")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(UserDto.class);
        //Then
        Assertions.assertEquals(address, userDto.getAddress());
        Assertions.assertEquals(emailAddress, userDto.getEmailAddress());
        Assertions.assertEquals("T", userDto.getFirstName());
        Assertions.assertEquals("M", userDto.getLastName());
        Assertions.assertEquals("789641230", userDto.getPhoneNumber());
        Assertions.assertEquals(Role.CUSTOMER, userDto.getRole());
        Assertions.assertEquals("tm", userDto.getPassword());


    }
}
