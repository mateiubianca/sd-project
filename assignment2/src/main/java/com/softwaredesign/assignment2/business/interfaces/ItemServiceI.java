package com.softwaredesign.assignment2.business.interfaces;

import com.softwaredesign.assignment2.dto.ItemDTO;

public interface ItemServiceI {

    public ItemDTO getItemTypeFlowerById(int id);

    public ItemDTO getItemTypeBouquetById(int id);

}
