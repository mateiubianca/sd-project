package com.softwaredesign.assignment2.business.interfaces;

import com.softwaredesign.assignment2.dto.BouquetDTO;
import com.softwaredesign.assignment2.dto.BouquetFlowerDTO;

import java.util.ArrayList;

public interface BouquetServiceI {


    public ArrayList<BouquetDTO> getAllBouquets();

    public BouquetDTO getBouquetById(int id);

    public void createBouquet(String name, ArrayList<BouquetFlowerDTO> flowers);

    public void updateBouquet(int id, String name, ArrayList<BouquetFlowerDTO> flowers);

    public void deleteBouquet(int id);

}
