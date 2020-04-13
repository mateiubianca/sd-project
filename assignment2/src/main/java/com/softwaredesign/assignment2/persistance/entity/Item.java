package com.softwaredesign.assignment2.persistance.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@Builder
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter
@Table(name = "items")
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private ItemType type;

    @Column
    private Integer item;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item", cascade =CascadeType.REMOVE)
    private List<Order> orders = new ArrayList<>();
}
