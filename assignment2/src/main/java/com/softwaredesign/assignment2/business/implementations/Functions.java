package com.softwaredesign.assignment2.business.implementations;

public class Functions {

    public static boolean validateLoginInput(String mail, String pass){

        return !mail.equals("") && !pass.equals("");
    }

    public static boolean validateNewUserInput(String username, String pass, String wallet){
        if(!username.equals("") && !wallet.equals("") && !pass.equals("")){
            try{
                int walletValue = Integer.parseInt(wallet);
                if(walletValue >= 0){
                    return true;
                } else{
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        } else{
            return false;
        }
    }

    public static boolean validateNewFlowerToBouquetInput(String name, String no){
        if(!name.equals("") && !no.equals("")){
            try{
                int priceValue = Integer.parseInt(no);
                return true;
            } catch (NumberFormatException e){
                return false;
            }
        } else{
            return false;
        }
    }

    public static boolean validateNewFlowerInput(String name, String price){
        if(!name.equals("") && !price.equals("")){
            try{
                int priceValue = Integer.parseInt(price);
                if(priceValue > 0){
                    return true;
                } else{
                    return false;
                }
            } catch (NumberFormatException e){
                return false;
            }
        } else{
            return false;
        }
    }

    public static boolean validateNewBouquetInput(String name, int flowerNo){
        return !name.equals("") && flowerNo > 0;
    }

}
