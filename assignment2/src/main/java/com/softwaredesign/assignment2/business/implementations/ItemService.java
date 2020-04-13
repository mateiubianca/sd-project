package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.interfaces.ItemServiceI;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.persistance.entity.Item;
import com.softwaredesign.assignment2.persistance.entity.ItemType;
import com.softwaredesign.assignment2.persistance.repo.ItemRepo;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ItemService implements ItemServiceI {

    @Inject
    private ItemRepo itemRepo;

    public ItemDTO getItemTypeFlowerById(int id){
        List<Item> items = itemRepo.findAllByType(ItemType.FLOWER);

        for (Item i: items ) {
            if(i.getItem() == id){
                return new ItemDTO(i);
            }
        }

        return null;
    }

    public ItemDTO getItemTypeBouquetById(int id){
        List<Item> items = itemRepo.findAllByType(ItemType.BOUQUET);

        for (Item i: items ) {
            if(i.getItem() == id){
                return new ItemDTO(i);
            }
        }

        return null;
    }

}
