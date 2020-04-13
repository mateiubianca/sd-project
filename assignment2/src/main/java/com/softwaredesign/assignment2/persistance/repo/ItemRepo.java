package com.softwaredesign.assignment2.persistance.repo;

import com.softwaredesign.assignment2.persistance.entity.Item;
import com.softwaredesign.assignment2.persistance.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Integer> {

    public List<Item> findAllByType(ItemType type);

    public Item findById(int id);

}
