package com.softwaredesign.assignment2.dto;

import com.softwaredesign.assignment2.persistance.entity.Role;
import com.softwaredesign.assignment2.persistance.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@Builder                        // Builder Pattern (Lab 2)
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter                         // Setters for all fields of the class
@Data
public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private Integer wallet;

    private Role role;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.wallet = user.getWallet();
        this.role = user.getRole();
    }

}
