package com.softwaredesign.assignment2.persistance.repo;

import com.softwaredesign.assignment2.persistance.entity.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BouquetRepo extends JpaRepository<Bouquet, Integer> {

    public Bouquet findById(int id);

}
