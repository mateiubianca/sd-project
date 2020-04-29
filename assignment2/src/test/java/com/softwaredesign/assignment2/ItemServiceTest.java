package com.softwaredesign.assignment2;

import com.softwaredesign.assignment2.business.implementations.ItemService;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.persistance.entity.Item;
import com.softwaredesign.assignment2.persistance.entity.ItemType;
import com.softwaredesign.assignment2.persistance.repo.ItemRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemServiceTest {

    @Mock
    private ItemRepo itemRepo;
    @InjectMocks
    private ItemService itemService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getItemTypeFlowerById(){
        Item item1 = Item.builder().id(1).type(ItemType.FLOWER).item(1).build();
        ItemDTO itemDTO1 = new ItemDTO(item1);

        List<Item> items = new ArrayList<>();
        items.add(item1);

        when(itemRepo.findAllByType(ItemType.FLOWER)).thenReturn(items);

        ItemDTO obtainedItem = itemService.getItemTypeFlowerById(1);

        Assert.assertTrue(new ReflectionEquals(itemDTO1).matches(obtainedItem));
    }

    @Test
    public void getItemTypeBouquetById(){
        Item item1 = Item.builder().id(1).type(ItemType.BOUQUET).item(1).build();
        ItemDTO itemDTO1 = new ItemDTO(item1);

        List<Item> items = new ArrayList<>();
        items.add(item1);

        when(itemRepo.findAllByType(ItemType.BOUQUET)).thenReturn(items);

        ItemDTO obtainedItem = itemService.getItemTypeBouquetById(1);

        Assert.assertTrue(new ReflectionEquals(itemDTO1).matches(obtainedItem));
    }

}
