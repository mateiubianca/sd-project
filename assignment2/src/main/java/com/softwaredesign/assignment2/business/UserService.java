package com.softwaredesign.assignment2.business;

import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.Role;
import com.softwaredesign.assignment2.persistance.entity.User;
import com.softwaredesign.assignment2.persistance.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

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

}
