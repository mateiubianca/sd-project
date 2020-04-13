package com.softwaredesign.assignment2.business.interfaces;

import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.dto.UserDTO;

public interface FunctionsI {

    public boolean validateLoginInput(String mail, String pass);

    public boolean validateNewUserInput(String username, String pass, String wallet);

    public boolean validateNewFlowerToBouquetInput(String name, String no);

    public boolean validateNewFlowerInput(String name, String price);

    public boolean validateNewFlowerInputUnique(String name);

    public boolean validateNewBouquetInput(String name, int flowerNo);

    public boolean validateOrderInput(UserDTO userDTO, ItemDTO itemDTO);

    public boolean validateReportInput(String name);

}
