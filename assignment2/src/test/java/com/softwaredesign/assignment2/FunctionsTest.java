package com.softwaredesign.assignment2;

import com.softwaredesign.assignment2.business.implementations.Functions;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import com.softwaredesign.assignment2.persistance.repo.FlowerRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class FunctionsTest {

    @Mock
    private FlowerRepo flowerRepo;
    @InjectMocks
    private Functions functions;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validateLoginInput(){
        Assert.assertFalse(functions.validateLoginInput("", ""));
        Assert.assertFalse(functions.validateLoginInput("user", ""));
        Assert.assertFalse(functions.validateLoginInput("", "pass"));
        Assert.assertTrue(functions.validateLoginInput("user", "pass"));
    }

    @Test
    public void validateNewUserInput(){
        Assert.assertFalse(functions.validateNewUserInput("", "", "0"));
        Assert.assertFalse(functions.validateNewUserInput("user", "", "0"));
        Assert.assertFalse(functions.validateNewUserInput("", "pass", "0"));
        Assert.assertFalse(functions.validateNewUserInput("user", "pass", "incorrect"));
        Assert.assertTrue(functions.validateNewUserInput("user", "pass", "0"));
    }

    @Test
    public void validateNewFlowerToBouquetInput(){
        Assert.assertFalse(functions.validateNewFlowerToBouquetInput("", ""));
        Assert.assertFalse(functions.validateNewFlowerToBouquetInput("flower", "incorrect"));
        Assert.assertTrue(functions.validateNewFlowerToBouquetInput("flower", "0"));
    }

    @Test
    public void validateNewFlowerInput(){
        Assert.assertFalse(functions.validateNewFlowerInput("", ""));
        Assert.assertFalse(functions.validateNewFlowerInput("flower", "incorrect"));
        Assert.assertFalse(functions.validateNewFlowerInput("flower", "-5"));
        Assert.assertTrue(functions.validateNewFlowerInput("flower", "5"));
    }

    @Test
    public void validateNewFlowerInputUnique(){
        Flower flower = Flower.builder().id(1).name("f").price(10).build();
        List<Flower> flowersList = new ArrayList<>();
        flowersList.add(flower);

        when(flowerRepo.findAll()).thenReturn(flowersList);

        Assert.assertFalse(functions.validateNewFlowerInputUnique("f"));
        Assert.assertTrue(functions.validateNewFlowerInputUnique("g"));
    }

    @Test
    public void validateReportInput(){
        Assert.assertFalse(functions.validateReportInput(""));
        Assert.assertTrue(functions.validateReportInput("txt"));
    }

    @Test
    public void validateNewBouquetInput(){
        Assert.assertFalse(functions.validateNewBouquetInput("", 0));
        Assert.assertFalse(functions.validateNewBouquetInput("flower", 0));
        Assert.assertTrue(functions.validateNewBouquetInput("flower", 5));
    }

    @Test
    public void validateOrderInput(){
        UserDTO userDTO = UserDTO.builder().build();
        ItemDTO itemDTO = ItemDTO.builder().build();
        Assert.assertFalse(functions.validateOrderInput(null, null));
        Assert.assertFalse(functions.validateOrderInput(null, itemDTO));
        Assert.assertFalse(functions.validateOrderInput(userDTO, null));
        Assert.assertTrue(functions.validateOrderInput(userDTO, itemDTO));
    }
}
