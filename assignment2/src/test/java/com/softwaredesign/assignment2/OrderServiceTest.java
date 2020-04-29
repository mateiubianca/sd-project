package com.softwaredesign.assignment2;

import com.softwaredesign.assignment2.business.error.InsufficientFundsException;
import com.softwaredesign.assignment2.business.implementations.OrderService;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.dto.OrderDTO;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.*;
import com.softwaredesign.assignment2.persistance.repo.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private ItemRepo itemRepo;
    @Mock
    private BouquetRepo bouquetRepo;
    @Mock
    private FlowerRepo flowerRepo;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrder() throws InsufficientFundsException {
        User user = User.builder().id(1).role(Role.USER).username("a").password("a").wallet(20).build();
        UserDTO userDTO = new UserDTO(user);

        Flower flower = Flower.builder().id(1).name("f").price(10).build();

        Item item = Item.builder().id(1).type(ItemType.FLOWER).item(1).build();
        ItemDTO itemDTO = new ItemDTO(item);

        Order order = Order.builder().id(1).item(item).user(user).build();
        OrderDTO orderDTO = new OrderDTO(order);

        when(userRepo.findById(1)).thenReturn(user);
        when(itemRepo.findById(1)).thenReturn(item);
        when(flowerRepo.findById(1)).thenReturn(flower);

        Mockito.doAnswer(invocationOnMock -> {
            if(invocationOnMock.getArguments()[0] instanceof Order)
                return order;
            return null;
        }).when(orderRepo).save(Mockito.any(Order.class));

        OrderDTO obtainedOrder = orderService.createOrder(userDTO, itemDTO);
        Assert.assertTrue(new ReflectionEquals(obtainedOrder).matches(orderDTO));
    }

}
