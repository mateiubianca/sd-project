package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.interfaces.BouquetServiceI;
import com.softwaredesign.assignment2.dto.BouquetDTO;
import com.softwaredesign.assignment2.dto.BouquetFlowerDTO;
import com.softwaredesign.assignment2.persistance.entity.Bouquet;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import com.softwaredesign.assignment2.persistance.repo.BouquetFlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.BouquetRepo;
import com.softwaredesign.assignment2.persistance.repo.FlowerRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class BouquetService implements BouquetServiceI {

    @Inject
    private BouquetRepo bouquetRepo;
    @Inject
    private FlowerRepo flowerRepo;
    @Inject
    private BouquetFlowerRepo bouquetFlowerRepo;

    public ArrayList<BouquetDTO> getAllBouquets(){
        ArrayList<BouquetDTO> bouquetDTO = new ArrayList<>();
        List<Bouquet> bouquets = bouquetRepo.findAll();

        for(Bouquet b : bouquets){
            bouquetDTO.add(new BouquetDTO(b));
        }

        return bouquetDTO;
    }

    public void createBouquet(String name,  ArrayList<BouquetFlowerDTO> flowers){

        ArrayList<BouquetFlower> bouquetFlowers = new ArrayList<>();

        Bouquet bouquet = new Bouquet();

        flowers.forEach(bf -> {
            Flower flower = Flower.builder()
                    .id(bf.getFlower().getId())
                    .name(bf.getFlower().getName())
                    .price(bf.getFlower().getPrice())
                    .bouquetFlowers(bf.getFlower().getBouquetFlowers()).build();

            BouquetFlower bouquetFlower = BouquetFlower.builder().flower(flower).bouquet(bouquet).quantity(bf.getQuantity()).build();
            bouquetFlowers.add(bouquetFlower);
            flower.getBouquetFlowers().add(bouquetFlower);

            //bouquet.getBouquetFlowers().add(bouquetFlower);
        });

        bouquet.setName(name);
        bouquet.getBouquetFlowers().addAll(bouquetFlowers);

        bouquetRepo.save(bouquet);

        bouquet.getBouquetFlowers().forEach(b ->{
            bouquetFlowerRepo.save(b);
        });

    }

    public void updateBouquet(int id, String name, ArrayList<BouquetFlowerDTO> flowers){


        ArrayList<BouquetFlower> bouquetFlowers = new ArrayList<>();

        Bouquet bouquet = bouquetRepo.findById(id);

        flowers.forEach(bf -> {
            AtomicBoolean found = new AtomicBoolean(false);
            bouquet.getBouquetFlowers().forEach(bouquetFlower -> {
                if(bf.getFlower().getName().equals(bouquetFlower.getFlower().getName())){
                    bouquetFlower.setQuantity(bf.getQuantity());
                    found.set(true);
                }
            });

            if (!found.get()){
                Flower flower = Flower.builder()
                        .id(bf.getFlower().getId())
                        .name(bf.getFlower().getName())
                        .price(bf.getFlower().getPrice())
                        .bouquetFlowers(bf.getFlower().getBouquetFlowers()).build();

                BouquetFlower bouquetFlower = BouquetFlower.builder().flower(flower).bouquet(bouquet).quantity(bf.getQuantity()).build();
                bouquetFlowers.add(bouquetFlower);
                flower.getBouquetFlowers().add(bouquetFlower);
            }

            //bouquet.getBouquetFlowers().add(bouquetFlower);
        });

        bouquet.setName(name);
        bouquet.getBouquetFlowers().addAll(bouquetFlowers);

        bouquetRepo.save(bouquet);

        bouquet.getBouquetFlowers().forEach(b ->{
            bouquetFlowerRepo.save(b);
        });
    }

    public void deleteBouquet(int id){
        Bouquet bouquet = bouquetRepo.findById(id);

/*        List<BouquetFlower> bouquetFlowers1 = bouquetFlowerRepo.findAllByBouquet(bouquet);

        for (BouquetFlower bf1: bouquetFlowers1) {
            bouquetFlowerRepo.delete(bf1);
        }*/

        bouquetRepo.delete(bouquet);
    }

}
