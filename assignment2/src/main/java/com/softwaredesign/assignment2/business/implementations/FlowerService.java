package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.interfaces.FlowerServiceI;
import com.softwaredesign.assignment2.dto.FlowerDTO;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import com.softwaredesign.assignment2.persistance.entity.Item;
import com.softwaredesign.assignment2.persistance.entity.ItemType;
import com.softwaredesign.assignment2.persistance.repo.BouquetFlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.BouquetRepo;
import com.softwaredesign.assignment2.persistance.repo.FlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.ItemRepo;
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
    private ItemRepo itemRepo;

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

    public FlowerDTO createFlower(String name, int price){
        Flower flower = Flower.builder().name(name).price(price).bouquetFlowers(new ArrayList<>()).build();
        Flower saved = flowerRepo.save(flower);
        Item item = Item.builder().type(ItemType.FLOWER).item(saved.getId()).build();
        itemRepo.save(item);

        return new FlowerDTO(saved);
    }

    public FlowerDTO updateFlower(int id, String name, int price){
        Flower flower = flowerRepo.findById(id);
        flower.setName(name);
        flower.setPrice(price);
        Flower saved = flowerRepo.save(flower);

        return new FlowerDTO(saved);
    }

    public void deleteFlower(int id){
        Flower flower = flowerRepo.findById(id);

        List<BouquetFlower> bouquetFlowers = bouquetFlowerRepo.findAllByFlower(flower);

        List<Item> items = itemRepo.findAllByType(ItemType.FLOWER);

        items.forEach(i->{
            if(i.getItem() == id){
                itemRepo.delete(i);
            }
        });

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
