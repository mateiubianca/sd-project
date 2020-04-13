package com.softwaredesign.assignment2.business.interfaces;

import com.softwaredesign.assignment2.business.error.InsufficientFundsException;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.dto.UserDTO;

public interface OrderServiceI {

    public void createOrder(UserDTO userDTO, ItemDTO item) throws InsufficientFundsException;

    public void generateOrderReport(String type, String path);

}
