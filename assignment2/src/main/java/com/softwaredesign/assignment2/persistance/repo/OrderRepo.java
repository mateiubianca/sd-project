package com.softwaredesign.assignment2.persistance.repo;

import com.softwaredesign.assignment2.persistance.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
