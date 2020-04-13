package com.softwaredesign.assignment2.dto;

import com.softwaredesign.assignment2.persistance.entity.Item;
import com.softwaredesign.assignment2.persistance.entity.Order;
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
public class OrderDTO {

    private Integer id;

    private User user;

    private Item item;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.user = order.getUser();
        this.item = order.getItem();
    }

}
