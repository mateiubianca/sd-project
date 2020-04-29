package com.softwaredesign.assignment2.business.interfaces;

import com.softwaredesign.assignment2.dto.FlowerDTO;

import java.util.ArrayList;

public interface FlowerServiceI {

    public ArrayList<FlowerDTO> getAllFlowers();

    public FlowerDTO getFlowerById(int id);

    public FlowerDTO getFlowerByName(String name);

    public FlowerDTO createFlower(String name, int price);

    public FlowerDTO updateFlower(int id, String name, int price);

    public void deleteFlower(int id);

}
