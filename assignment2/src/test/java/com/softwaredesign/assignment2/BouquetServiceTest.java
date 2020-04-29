package com.softwaredesign.assignment2;

import com.softwaredesign.assignment2.business.implementations.BouquetService;
import com.softwaredesign.assignment2.dto.BouquetDTO;
import com.softwaredesign.assignment2.dto.BouquetFlowerDTO;
import com.softwaredesign.assignment2.persistance.entity.Bouquet;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import com.softwaredesign.assignment2.persistance.repo.BouquetFlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.BouquetRepo;
import com.softwaredesign.assignment2.persistance.repo.FlowerRepo;
import com.softwaredesign.assignment2.persistance.repo.ItemRepo;
import org.hamcrest.CoreMatchers;
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
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class BouquetServiceTest {
    @Mock
    private BouquetRepo bouquetRepo;
    @Mock
    private FlowerRepo flowerRepo;
    @Mock
    private BouquetFlowerRepo bouquetFlowerRepo;
    @Mock
    private ItemRepo itemRepo;

    @InjectMocks
    private BouquetService bouquetService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllBouquets(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).build();
        Bouquet bouquet = new Bouquet();

        BouquetFlower bouquetFlower = BouquetFlower.builder().flower(flower1).bouquet(bouquet).quantity(5).id(1).build();
        ArrayList<BouquetFlower> bouquetFlowers = new ArrayList<>();
        bouquetFlowers.add(bouquetFlower);

        bouquet.setName("b");
        bouquet.setId(1);
        bouquet.setBouquetFlowers(bouquetFlowers);

        BouquetDTO bouquetDTO = new BouquetDTO(bouquet);

        List<Bouquet> bouquets = new ArrayList<>();
        bouquets.add(bouquet);

        List<BouquetDTO> bouquetsDTO = new ArrayList<>();
        bouquetsDTO.add(bouquetDTO);

        when(bouquetRepo.findAll()).thenReturn(bouquets);
        List<BouquetDTO> obtainedBouquets = bouquetService.getAllBouquets();

        Assert.assertThat(obtainedBouquets, CoreMatchers.is(bouquetsDTO));
    }

    @Test
    public void getBouquetById(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).build();
        Bouquet bouquet = new Bouquet();

        BouquetFlower bouquetFlower = BouquetFlower.builder().flower(flower1).bouquet(bouquet).quantity(5).id(1).build();
        ArrayList<BouquetFlower> bouquetFlowers = new ArrayList<>();
        bouquetFlowers.add(bouquetFlower);

        bouquet.setName("b");
        bouquet.setId(1);
        bouquet.setBouquetFlowers(bouquetFlowers);

        BouquetDTO bouquetDTO = new BouquetDTO(bouquet);

        when(bouquetRepo.findById(1)).thenReturn(bouquet);

        BouquetDTO obtainedBouquet = bouquetService.getBouquetById(1);
        Assert.assertTrue(new ReflectionEquals(bouquetDTO).matches(obtainedBouquet));
    }

    @Test
    public void createBouquet(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).bouquetFlowers(new ArrayList<>()).build();
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

        BouquetDTO bouquetDTO = new BouquetDTO(bouquet);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Bouquet)
                return bouquet;
            return null;
        }).when(bouquetRepo).save(Mockito.any(Bouquet.class));

        BouquetDTO obtainedBouquet = bouquetService.createBouquet("b", bouquetFlowersDTO);
        Assert.assertTrue(new ReflectionEquals(obtainedBouquet).matches(bouquetDTO));
    }

    @Test
    public void updateBouquet(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).bouquetFlowers(new ArrayList<>()).build();
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
        when(bouquetRepo.save(bouquet)).thenReturn(bouquet);

        bouquet.setName("c");
        BouquetDTO bouquetDTO = new BouquetDTO(bouquet);

        BouquetDTO obtainedBouquet = bouquetService.updateBouquet(1, "c", bouquetFlowersDTO);
        Assert.assertTrue(new ReflectionEquals(obtainedBouquet).matches(bouquetDTO));
    }

    @Test
    public void deleteBouquet(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).bouquetFlowers(new ArrayList<>()).build();
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

        BouquetDTO bouquetDTO = new BouquetDTO(bouquet);

        when(bouquetRepo.findById(1)).thenReturn(bouquet);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Bouquet)
                return bouquet;
            return null;
        }).when(bouquetRepo).delete(Mockito.any(Bouquet.class));

        bouquetService.deleteBouquet(1);

        when(bouquetRepo.findById(1)).thenReturn(null);

        Assert.assertEquals(bouquetRepo.findById(1),null);
    }
}
