package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.interfaces.FlowerServiceI;
import com.softwaredesign.assignment2.dto.FlowerDTO;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import com.softwaredesign.assignment2.persistance.repo.BouquetFlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.BouquetRepo;
import com.softwaredesign.assignment2.persistance.repo.FlowerRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowerService implements FlowerServiceI {

    @Inject
    private FlowerRepo flowerRepo;

    @Inject
    private BouquetRepo bouquetRepo;

    @Inject
    private BouquetFlowerRepo bouquetFlowerRepo;

    public ArrayList<FlowerDTO> getAllFlowers(){
        ArrayList<FlowerDTO> flowerDto = new ArrayList<>();
        List<Flower> flowers = flowerRepo.findAll();

        for(Flower f : flowers){
            flowerDto.add(new FlowerDTO(f));
        }

        return flowerDto;
    }

    public FlowerDTO getFlowerById(int id){
        return new FlowerDTO(flowerRepo.findById(id));
    }

    public FlowerDTO getFlowerByName(String name){
        return new FlowerDTO(flowerRepo.findByName(name));
    }

    public void createFlower(String name, int price){
        Flower flower = Flower.builder().name(name).price(price).bouquetFlowers(new ArrayList<>()).build();
        flowerRepo.save(flower);
    }

    public void updateFlower(int id, String name, int price){
        Flower flower = flowerRepo.findById(id);
        flower.setName(name);
        flower.setPrice(price);
        flowerRepo.save(flower);
    }

    public void deleteFlower(int id){
        Flower flower = flowerRepo.findById(id);

        List<BouquetFlower> bouquetFlowers = bouquetFlowerRepo.findAllByFlower(flower);

/*        for (BouquetFlower bf: bouquetFlowers) {
            int idBouquet = bf.getBouquet().getId();
            Bouquet bouquet = bouquetRepo.findById(idBouquet);
            List<BouquetFlower> bouquetFlowers1 = bouquetFlowerRepo.findAllByBouquet(bouquet);

            for (BouquetFlower bf1: bouquetFlowers1) {
                if(bf1.getFlower().getId() == id){
                    bouquetFlowerRepo.delete(bf1);
                }
            }

            //bouquetRepo.delete(bouquet);
        }*/

        flowerRepo.delete(flower);
    }

}
