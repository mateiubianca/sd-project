package com.softwaredesign.assignment2.business;

public class Functions {

    public static boolean validateLoginInput(String mail, String pass){

        return !mail.equals("") && !pass.equals("");
    }


}
