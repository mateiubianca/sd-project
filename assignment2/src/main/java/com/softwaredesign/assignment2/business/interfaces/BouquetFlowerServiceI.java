package com.softwaredesign.assignment2.business.interfaces;

import com.softwaredesign.assignment2.dto.BouquetFlowerDTO;

import java.util.ArrayList;

public interface BouquetFlowerServiceI {

    public ArrayList<BouquetFlowerDTO> getBouquetFlowersByBouquetId(int id);
    public void deleteBouquetFlowersById(int id);

}
