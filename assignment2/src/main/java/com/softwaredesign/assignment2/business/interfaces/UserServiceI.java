package com.softwaredesign.assignment2.business.interfaces;

import com.softwaredesign.assignment2.dto.UserDTO;

import java.util.ArrayList;

public interface UserServiceI {

    public UserDTO login(String username, String password);

    public ArrayList<UserDTO> getAllUsers();

    public UserDTO getUserByUsername(String username);

    public UserDTO createUser(String username, String password, int wallet);

    public UserDTO updateUser(int id, String username, String password, int wallet);

    public void deleteUser(int id);

}
