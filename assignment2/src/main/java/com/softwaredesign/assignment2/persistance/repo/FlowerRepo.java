package com.softwaredesign.assignment2.persistance.repo;

import com.softwaredesign.assignment2.persistance.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepo extends JpaRepository<Flower, Integer> {

    public Flower findById(int id);

    public Flower findByName(String name);

}
