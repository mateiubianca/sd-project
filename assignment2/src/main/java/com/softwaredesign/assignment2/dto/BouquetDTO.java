package com.softwaredesign.assignment2.dto;

import com.softwaredesign.assignment2.persistance.entity.Bouquet;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class BouquetDTO {

    private Integer id;

    private String name;

    private int price;

    private List<BouquetFlower> bouquetFlowers;

    public BouquetDTO(Bouquet bouquet){
        this.id = bouquet.getId();
        this.name = bouquet.getName();
        this.price = bouquet.calculatePrice();
        this.bouquetFlowers = bouquet.getBouquetFlowers();
    }

}
