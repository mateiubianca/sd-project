package com.softwaredesign.assignment2.business.error;

public class InsufficientFundsException extends Exception{

    public InsufficientFundsException(){
        super();
    }

    public InsufficientFundsException(String msg){
        super(msg);
    }

}
