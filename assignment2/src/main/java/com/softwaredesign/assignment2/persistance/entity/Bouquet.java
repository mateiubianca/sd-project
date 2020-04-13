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
@Table(name = "bouquet")
@Entity

public class Bouquet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bouquet", cascade = CascadeType.REMOVE)
    private List<BouquetFlower> bouquetFlowers = new ArrayList<>();


    public int calculatePrice(){
        int price = 0;
        for (BouquetFlower f: this.bouquetFlowers) {
            Flower flower = f.getFlower();
            price += f.getQuantity() * flower.getPrice();
        }
        return price;
    }

    public Bouquet(String name, ArrayList<BouquetFlower> bouquetFlowers) {
        this.name = name;
        for(BouquetFlower bouquetFlower : bouquetFlowers) bouquetFlower.setBouquet(this);
        this.bouquetFlowers = bouquetFlowers;
    }

}
