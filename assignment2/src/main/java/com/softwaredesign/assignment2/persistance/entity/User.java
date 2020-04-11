package com.softwaredesign.assignment2.persistance.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@Builder
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private int wallet;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private Role role;
}
