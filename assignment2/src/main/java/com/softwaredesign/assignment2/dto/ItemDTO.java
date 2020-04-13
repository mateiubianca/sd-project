package com.softwaredesign.assignment2.dto;

import com.softwaredesign.assignment2.persistance.entity.Item;
import com.softwaredesign.assignment2.persistance.entity.ItemType;
import com.softwaredesign.assignment2.persistance.entity.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@Builder                        // Builder Pattern (Lab 2)
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter                         // Setters for all fields of the class
@Data
public class ItemDTO {

    private Integer id;

    private ItemType type;

    private Integer item;

    private List<Order> orders = new ArrayList<>();

    public ItemDTO(Item item){
        this.id = item.getId();
        this.type = item.getType();
        this.item = item.getItem();
        this.orders = item.getOrders();
    }
}
