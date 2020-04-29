package com.softwaredesign.assignment2;

import com.softwaredesign.assignment2.business.implementations.BouquetFlowerService;
import com.softwaredesign.assignment2.dto.BouquetFlowerDTO;
import com.softwaredesign.assignment2.persistance.entity.Bouquet;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import com.softwaredesign.assignment2.persistance.repo.BouquetFlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.BouquetRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BouquetFlowerServiceTest {
    @Mock
    private BouquetFlowerRepo bouquetFlowerRepo;

    @Mock
    private BouquetRepo bouquetRepo;

    @InjectMocks
    private BouquetFlowerService bouquetFlowerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBouquetFlowersByBouquetId(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).build();
        Bouquet bouquet = new Bouquet();

        BouquetFlower bouquetFlower = BouquetFlower.builder().flower(flower1).bouquet(bouquet).quantity(5).id(1).build();
        BouquetFlowerDTO bouquetFlowerDTO = new BouquetFlowerDTO(bouquetFlower);

        ArrayList<BouquetFlower> bouquetFlowers = new ArrayList<>();
        bouquetFlowers.add(bouquetFlower);
        ArrayList<BouquetFlowerDTO> bouquetFlowersDTO = new ArrayList<>();
        bouquetFlowersDTO.add(bouquetFlowerDTO);

        bouquet.setName("b");
        bouquet.setId(1);
        bouquet.setBouquetFlowers(bouquetFlowers);

        when(bouquetRepo.findById(1)).thenReturn(bouquet);
        when(bouquetFlowerRepo.findAllByBouquet(bouquet)).thenReturn(bouquetFlowers);

        ArrayList<BouquetFlowerDTO> obtainedBouquetFlowers = bouquetFlowerService.getBouquetFlowersByBouquetId(1);
        Assert.assertTrue(new ReflectionEquals(bouquetFlowersDTO).matches(obtainedBouquetFlowers));

    }

    @Test
    public void deleteBouquetFlowersById(){
        BouquetFlower bouquetFlower = BouquetFlower.builder().quantity(5).id(1).build();

        when(bouquetFlowerRepo.findById(1)).thenReturn(bouquetFlower);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof BouquetFlower)
                return bouquetFlower;
            return null;
        }).when(bouquetFlowerRepo).delete(Mockito.any(BouquetFlower.class));

        bouquetFlowerService.deleteBouquetFlowersById(1);

        when(bouquetFlowerRepo.findById(1)).thenReturn(null);

        Assert.assertEquals(bouquetFlowerRepo.findById(1),null);
    }
}
