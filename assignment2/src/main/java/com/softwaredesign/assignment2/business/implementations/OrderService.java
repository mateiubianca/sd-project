package com.softwaredesign.assignment2.business.implementations;

import com.softwaredesign.assignment2.business.error.InsufficientFundsException;
import com.softwaredesign.assignment2.business.interfaces.OrderServiceI;
import com.softwaredesign.assignment2.business.report.Report;
import com.softwaredesign.assignment2.business.report.ReportFactory;
import com.softwaredesign.assignment2.dto.ItemDTO;
import com.softwaredesign.assignment2.dto.OrderDTO;
import com.softwaredesign.assignment2.dto.UserDTO;
import com.softwaredesign.assignment2.persistance.entity.*;
import com.softwaredesign.assignment2.persistance.repo.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService implements OrderServiceI {

    @Inject
    private OrderRepo orderRepo;
    @Inject
    private UserRepo userRepo;
    @Inject
    private ItemRepo itemRepo;
    @Inject
    private BouquetRepo bouquetRepo;
    @Inject
    private FlowerRepo flowerRepo;

    public OrderDTO createOrder(UserDTO userDTO, ItemDTO itemDTO) throws InsufficientFundsException {
        User user = userRepo.findById(userDTO.getId().intValue());
        Item item = itemRepo.findById(itemDTO.getId().intValue());

        if(item.getType().equals(ItemType.FLOWER)){
            Flower flower = flowerRepo.findById(item.getItem().intValue());
            if(user.getWallet() < flower.getPrice()){
                throw new InsufficientFundsException("Not enough money in wallet to buy the flower");
            }
            user.setWallet(user.getWallet() - flower.getPrice());
        } else{
            Bouquet bouquet = bouquetRepo.findById(item.getItem().intValue());
            int price = bouquet.calculatePrice();
            if(user.getWallet() < price){
                throw new InsufficientFundsException("Not enough money in wallet to buy the bouquet");
            }
            user.setWallet(user.getWallet() - price);
        }

        userRepo.save(user);

        Order order = Order.builder().item(item).user(user).build();
        Order saved = orderRepo.save(order);

        return new OrderDTO(saved);
    }

    public void generateOrderReport(String type, String path){
        StringBuilder ordersStr = new StringBuilder();

        List<Order> orders = orderRepo.findAll();
        HashMap<Item, Integer> itemsInOrders = new HashMap<>();

        orders.forEach(o -> {
            Integer oldValue = itemsInOrders.put(o.getItem(), 1);
            if(oldValue != null){
                itemsInOrders.put(o.getItem(), oldValue + 1);
            }
        });

        itemsInOrders.forEach((key, value) -> {
            if(key.getType().equals(ItemType.FLOWER)){
                Flower flower = flowerRepo.findById(key.getItem().intValue());
                ordersStr.append("The flower ").append(flower.getName()).append(" was purchased ").append(value).append(" times\n");
            } else{
                Bouquet bouquet = bouquetRepo.findById(key.getItem().intValue());
                ordersStr.append("The bouquet ").append(bouquet.getName()).append(" was purchased ").append(value).append(" times\n");
            }
        });

        System.out.println(ordersStr.toString());

        ReportFactory reportFactory = new ReportFactory();
        Report report = reportFactory.getReport(type);
        report.generateReport(ordersStr.toString(), path);

    }

}
