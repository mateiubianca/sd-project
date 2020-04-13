package com.softwaredesign.assignment2.persistance.repo;

import com.softwaredesign.assignment2.persistance.entity.Bouquet;
import com.softwaredesign.assignment2.persistance.entity.BouquetFlower;
import com.softwaredesign.assignment2.persistance.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BouquetFlowerRepo extends JpaRepository<BouquetFlower, Integer> {

    public List<BouquetFlower> findAllByFlower(Flower flower);

    public List<BouquetFlower> findAllByBouquet(Bouquet bouquet);

    public BouquetFlower findById(int id);


}
