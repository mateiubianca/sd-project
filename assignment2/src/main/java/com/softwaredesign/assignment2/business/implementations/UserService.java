package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.interfaces.UserServiceI;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.Role;
import com.softwaredesign.assignment2.persistance.entity.User;
import com.softwaredesign.assignment2.persistance.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceI {

    @Inject
    private UserRepo userRepo;

    public UserDTO login(String username, String password){
        List<User> users = userRepo.findAllByUsername(username);
        if(users.size() > 0){
            User user = users.get(0);
            if(user.getPassword().equals(password)){
                return new UserDTO(user);
            }
        }

        return null;
    }

    public ArrayList<UserDTO> getAllUsers(){
        ArrayList<UserDTO> usersDto = new ArrayList<>();
        List<User> users = userRepo.findAllByRole(Role.USER);

        for(User u : users){
            usersDto.add(new UserDTO(u));
        }

        return usersDto;
    }

    public UserDTO getUserByUsername(String username){
        List<User> users = userRepo.findAllByUsername(username);
        if(users.size() > 0) {
            User user = users.get(0);
            return new UserDTO(user);
        }

        return null;
    }

    public UserDTO createUser(String username, String password, int wallet){
        User user = User.builder().username(username).password(password).wallet(wallet).role(Role.USER).build();
        User saved = userRepo.save(user);
        return new UserDTO(saved);
    }

    public UserDTO updateUser(int id, String username, String password, int wallet){
        User user = userRepo.findById(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setWallet(wallet);
        User updated = userRepo.save(user);
        return new UserDTO(updated);
    }

    public void deleteUser(int id){
        User user = userRepo.findById(id);
        userRepo.delete(user);
    }

}
