package com.softwaredesign.assignment2.persistance.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor             // Creates constructor with all of the fields as arguments
@NoArgsConstructor              // Creates constructor with no arguments (Default)
@Builder
@FieldDefaults(level = PRIVATE) // Sets the visibility of all fields to PRIVATE
@ToString                       // ToString method implementation for class
@Getter                         // Getters for all fields of the class
@Setter
@Table(name = "bouquet_flower")
@Entity

public class BouquetFlower implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bouquet_id")
    private Bouquet bouquet;

   // @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    @Column
    private int quantity;

    public BouquetFlower(Flower flower, int quantity){
        this.flower = flower;
        this.quantity = quantity;
    }

}
