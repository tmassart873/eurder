package com.switchfully.eurder.api;

import com.switchfully.eurder.domain.address.Address;
import com.switchfully.eurder.domain.email.EmailAddress;
import com.switchfully.eurder.domain.user.Role;
import com.switchfully.eurder.domain.user.User;
import com.switchfully.eurder.exceptions.UserNotAuthorizedException;
import com.switchfully.eurder.repository.UserRepository;
import com.switchfully.eurder.service.dtos.userdto.CreateUserDto;
import com.switchfully.eurder.service.dtos.userdto.UserDto;
import com.switchfully.eurder.service.mappers.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = TestApplication.class)
public class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;
    @Autowired
    private UserMapper userMapper = new UserMapper();
    @Autowired
    private UserRepository userRepository;

    Address address;
    EmailAddress emailDirk;
    EmailAddress emailMarc;
    CreateUserDto createDirk;
    User userMarc;
    User userDirk;
    UserDto userDtoMarc;
    static boolean isSetupDone = false;

    @BeforeEach
    void setUp() {
        if (!isSetupDone) {
            initTest();
            userController.createNewUser(createDirk, "Basic ");
            userDirk = userRepository.getUserByEmail("dirk@order.com");
            isSetupDone = true;
        }
    }

    //Story 1 : Create a customer account
    @Test
    void registerANewUser_whenPostANewUser_thenUserInUserRepository() {
        //Given

        //When
        userDirk = userRepository.getUserByEmail("dirk@order.com");

        //Then
        Assertions.assertTrue(userRepository.getAllUsers().contains(userDirk));
        Assertions.assertFalse(userRepository.getAllUsers().contains(userMarc));
    }

    //Story 7 : View all customers
    @Test
    void givenAdmin_getAllCustomers_thenReturnsAllTheCustomers() {
        //Given

        //When
        List<UserDto> actualList = userController.getAllCustomers("Basic YWRtaW4udG1Ab3JkZXIuY29tOmFkbWluX3Rt");
        List<UserDto> expectedList = userRepository.getAllUsers().stream().filter(user -> user.getRole().equals(Role.CUSTOMER)).map(user -> userMapper.mapUserToUserDto(user)).toList();

        //Then
        Assertions.assertTrue(actualList.containsAll(expectedList));
    }

    //Story 7 : View all customers
    @Test
    void givenCustomer_getAllCustomers_thenThrowException() {
        //Given

        //When
        Throwable exception = catchThrowable(() -> userController.getAllCustomers("Basic ZGlya0BvcmRlci5jb206ZGlyaw=="));

        //Then
        assertThat(exception).isInstanceOf(UserNotAuthorizedException.class).hasMessage("You are not allowed to do this action.");
    }

    @Test
    void givenAdmin_getACustomers_thenReturnsASpecificCustomer() {
        //Given
        UUID id = userDirk.getId();

        //When
        UserDto actualUserDto = userController.getSingleCustomer(id.toString(),"Basic YWRtaW4udG1Ab3JkZXIuY29tOmFkbWluX3Rt");
        UserDto expectedUserDto = userMapper.mapUserToUserDto(userDirk);

        //Then
        Assertions.assertEquals(actualUserDto,expectedUserDto);
    }

    public void initTest() {
        address = Address.AddressBuilder.addressBuilder()
                .withStreetName("Veldstraat")
                .withStreetNumber("68")
                .withPostCode("9000")
                .withCity("Ghent")
                .build();
        emailDirk = new EmailAddress("dirk", "order.com");
        emailMarc = new EmailAddress("marc", "order.com");
        createDirk = new CreateUserDto()
                .setFirstName("Dirk")
                .setLastName("M")
                .setAddress(address)
                .setEmailAddress(emailDirk)
                .setPassword("dirk")
                .setPhoneNumber("789641230")
                .setRole(Role.CUSTOMER);
        userMarc = User.UserBuilder.userBuilder()
                .withFirstName("Marc")
                .withLastName("M")
                .withEmailAddress(emailMarc)
                .withAddress(address)
                .withPhoneNumber("951357826")
                .withRole(Role.CUSTOMER)
                .build();
        userDtoMarc = userMapper.mapUserToUserDto(userMarc);
    }
}
