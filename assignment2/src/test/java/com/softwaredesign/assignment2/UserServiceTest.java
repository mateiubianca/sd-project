package com.softwaredesign.assignment2;

import com.softwaredesign.assignment2.business.implementations.UserService;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.Role;
import com.softwaredesign.assignment2.persistance.entity.User;
import com.softwaredesign.assignment2.persistance.repo.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest{

    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void login() {
        User user = User.builder().id(1).role(Role.USER).username("a").password("a").wallet(0).build();
        UserDTO userDTO = new UserDTO(user);
        List<User> usersList = new ArrayList<>();
        usersList.add(user);

        when(userRepo.findAllByUsername("a")).thenReturn(usersList);

        UserDTO obtainedUser = userService.login("a", "a");
        Assert.assertTrue(new ReflectionEquals(userDTO).matches(obtainedUser));
    }

    @Test
    void getAllUsers() {
        User user1 = User.builder().id(1).role(Role.USER).username("a").password("a").wallet(0).build();
        User user2 = User.builder().id(2).role(Role.USER).username("b").password("b").wallet(0).build();
        UserDTO userDTO1 = new UserDTO(user1);
        UserDTO userDTO2 = new UserDTO(user2);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        List<UserDTO> usersDTO = new ArrayList<>();
        usersDTO.add(userDTO1);
        usersDTO.add(userDTO2);

        when(userRepo.findAllByRole(Role.USER)).thenReturn(users);
        List<UserDTO> obtainedUsers = userService.getAllUsers();

        Assert.assertThat(obtainedUsers, CoreMatchers.is(usersDTO));
    }

    @Test
    public void getUserByUsername() {
        User user = User.builder().id(1).role(Role.USER).username("a").password("a").wallet(0).build();
        UserDTO userDTO = new UserDTO(user);
        List<User> usersList = new ArrayList<>();
        usersList.add(user);

        when(userRepo.findAllByUsername("a")).thenReturn(usersList);

        UserDTO obtainedUser = userService.getUserByUsername("a");
        Assert.assertTrue(new ReflectionEquals(userDTO).matches(obtainedUser));
    }

    @Test
    void createUser() {
        User user = User.builder().id(1).role(Role.USER).username("a").password("a").wallet(0).build();
        UserDTO userDTO = new UserDTO(user);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof User)
                return user;
            return null;
        }).when(userRepo).save(Mockito.any(User.class));

        UserDTO obtainedUser = userService.createUser("a", "a", 0);
        Assert.assertTrue(new ReflectionEquals(obtainedUser).matches(userDTO));
    }

    @Test
    void updateUser() {
        User user = User.builder().id(1).role(Role.USER).username("a").password("a").wallet(0).build();
        UserDTO userDTO = new UserDTO(user);

        when(userRepo.findById(1)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);

        User userUpdate = User.builder().id(1).role(Role.USER).username("b").password("b").wallet(0).build();
        UserDTO userUpdateDTO = new UserDTO(userUpdate);

        UserDTO obtainedUser = userService.updateUser(1, "b", "b", 0);
        Assert.assertTrue(new ReflectionEquals(obtainedUser).matches(userUpdateDTO));
    }

    @Test
    void deleteUser(){
        User user = User.builder().id(1).role(Role.USER).username("a").password("a").wallet(0).build();
        UserDTO userDTO = new UserDTO(user);

        when(userRepo.findById(1)).thenReturn(user);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof User)
                return user;
            return null;
        }).when(userRepo).delete(Mockito.any(User.class));

        userService.deleteUser(1);

        when(userRepo.findById(1)).thenReturn(null);

        Assert.assertEquals(userRepo.findById(1),null);
    }
}
