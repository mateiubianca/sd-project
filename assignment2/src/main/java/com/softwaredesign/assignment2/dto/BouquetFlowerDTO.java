package com.softwaredesign.assignment2.dto;

import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
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
public class BouquetFlowerDTO {

    private Integer id;

    private BouquetDTO bouquet;

    private FlowerDTO flower;

    private int quantity;

    public BouquetFlowerDTO(BouquetFlower bouquetFlower){
        this.id = bouquetFlower.getId();
        this.bouquet = new BouquetDTO(bouquetFlower.getBouquet());
        this.flower = new FlowerDTO(bouquetFlower.getFlower());
        this.quantity = bouquetFlower.getQuantity();
    }

    public BouquetFlowerDTO(FlowerDTO flowerDTO, int quantity){
        this.id = 0;
        this.bouquet = null;
        this.flower = flowerDTO;
        this.quantity = quantity;
    }

    public BouquetFlowerDTO(FlowerDTO flowerDTO, BouquetDTO bouquet, int quantity){
        this.id = 0;
        this.bouquet = bouquet;
        this.flower = flowerDTO;
        this.quantity = quantity;
    }

}
