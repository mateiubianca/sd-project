package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.interfaces.BouquetFlowerServiceI;
import com.softwaredesign.assignment2.dto.BouquetFlowerDTO;
import com.softwaredesign.assignment2.persistance.entity.Bouquet;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import com.softwaredesign.assignment2.persistance.repo.BouquetFlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.BouquetRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class BouquetFlowerService implements BouquetFlowerServiceI {

    @Inject
    private BouquetFlowerRepo bouquetFlowerRepo;

    @Inject
    private BouquetRepo bouquetRepo;

    public ArrayList<BouquetFlowerDTO> getBouquetFlowersByBouquetId(int id){
        ArrayList<BouquetFlowerDTO> bouquetFlowerDTO = new ArrayList<>();
        Bouquet bouquet = bouquetRepo.findById(id);
        List<BouquetFlower> bouquetFlowers = bouquetFlowerRepo.findAllByBouquet(bouquet);

        for(BouquetFlower b : bouquetFlowers){
            bouquetFlowerDTO.add(new BouquetFlowerDTO(b));
        }

        return bouquetFlowerDTO;
    }

    public void deleteBouquetFlowersById(int id){
        BouquetFlower bouquetFlower = bouquetFlowerRepo.findById(id);
        bouquetFlowerRepo.delete(bouquetFlower);
    }

}
