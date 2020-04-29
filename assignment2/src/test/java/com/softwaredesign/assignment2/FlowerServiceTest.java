package com.softwaredesign.assignment2;

import com.softwaredesign.assignment2.business.implementations.FlowerService;
import com.softwaredesign.assignment2.dto.FlowerDTO;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class FlowerServiceTest {

    @Mock
    private FlowerRepo flowerRepo;

    @Mock
    private BouquetRepo bouquetRepo;

    @Mock
    private ItemRepo itemRepo;

    @Mock
    private BouquetFlowerRepo bouquetFlowerRepo;

    @InjectMocks
    private FlowerService flowerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllFlowers(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).build();
        Flower flower2 = Flower.builder().id(2).name("f2").price(10).build();
        FlowerDTO flowerDTO1 = new FlowerDTO(flower1);
        FlowerDTO flowerDTO2 = new FlowerDTO(flower2);

        List<Flower> flowers = new ArrayList<>();
        flowers.add(flower1);
        flowers.add(flower2);

        List<FlowerDTO> flowersDTO = new ArrayList<>();
        flowersDTO.add(flowerDTO1);
        flowersDTO.add(flowerDTO2);

        when(flowerRepo.findAll()).thenReturn(flowers);
        List<FlowerDTO> obtainedFlowers = flowerService.getAllFlowers();

        Assert.assertThat(obtainedFlowers, CoreMatchers.is(flowersDTO));
    }

    @Test
    public void getFlowerById(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).build();
        FlowerDTO flowerDTO1 = new FlowerDTO(flower1);

        when(flowerRepo.findById(1)).thenReturn(flower1);

        FlowerDTO obtainedFlower = flowerService.getFlowerById(1);
        Assert.assertTrue(new ReflectionEquals(flowerDTO1).matches(obtainedFlower));
    }

    @Test
    public void getFlowerByName(){
        Flower flower1 = Flower.builder().id(1).name("f").price(10).build();
        FlowerDTO flowerDTO1 = new FlowerDTO(flower1);

        when(flowerRepo.findByName("f")).thenReturn(flower1);

        FlowerDTO obtainedFlower = flowerService.getFlowerByName("f");
        Assert.assertTrue(new ReflectionEquals(flowerDTO1).matches(obtainedFlower));
    }

    @Test
    public void createFlower(){
        Flower flower = Flower.builder().id(1).name("f").price(10).build();
        FlowerDTO flowerDTO = new FlowerDTO(flower);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Flower)
                return flower;
            return null;
        }).when(flowerRepo).save(Mockito.any(Flower.class));

        FlowerDTO obtainedFlower = flowerService.createFlower("f", 10);
        Assert.assertTrue(new ReflectionEquals(obtainedFlower).matches(flowerDTO));

    }

    @Test
    public void updateFlower(){
        Flower flower = Flower.builder().id(1).name("f").price(10).build();

        when(flowerRepo.findById(1)).thenReturn(flower);
        when(flowerRepo.save(flower)).thenReturn(flower);

        Flower flowerUpdate = Flower.builder().id(1).name("f").price(20).build();
        FlowerDTO flowerUpdateDTO = new FlowerDTO(flowerUpdate);

        FlowerDTO obtainedFlower = flowerService.updateFlower(1, "f", 20);
        Assert.assertTrue(new ReflectionEquals(obtainedFlower).matches(flowerUpdateDTO));
    }

    @Test
    public void deleteFlower(){
        Flower flower = Flower.builder().id(1).name("f").price(10).build();
        FlowerDTO flowerDTO = new FlowerDTO(flower);

        when(flowerRepo.findById(1)).thenReturn(flower);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Flower)
                return flower;
            return null;
        }).when(flowerRepo).delete(Mockito.any(Flower.class));

        flowerService.deleteFlower(1);

        when(flowerRepo.findById(1)).thenReturn(null);

        Assert.assertEquals(flowerRepo.findById(1),null);
    }
}
