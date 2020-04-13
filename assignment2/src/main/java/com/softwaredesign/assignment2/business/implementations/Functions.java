package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.interfaces.FunctionsI;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import com.softwaredesign.assignment2.persistance.repo.FlowerRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class Functions implements FunctionsI {

    @Inject
    private FlowerRepo flowerRepo;

    public boolean validateLoginInput(String mail, String pass){

        return !mail.equals("") && !pass.equals("");
    }

    public boolean validateNewUserInput(String username, String pass, String wallet){
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

    public boolean validateNewFlowerToBouquetInput(String name, String no){
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

    public boolean validateNewFlowerInput(String name, String price){
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

    public boolean validateNewFlowerInputUnique(String name){
        List<Flower> flowers = flowerRepo.findAll();
        AtomicBoolean ok = new AtomicBoolean(true);

        flowers.forEach(f->{
            if(f.getName().equals(name)){
                ok.set(false);
            }
        });

        return ok.get();
    }

    public boolean validateReportInput(String name){
        return !name.equals("");
    }

    public boolean validateNewBouquetInput(String name, int flowerNo){
        return !name.equals("") && flowerNo > 0;
    }

    public boolean validateOrderInput(UserDTO userDTO, ItemDTO itemDTO){
        return userDTO != null && itemDTO != null;
    }

}
