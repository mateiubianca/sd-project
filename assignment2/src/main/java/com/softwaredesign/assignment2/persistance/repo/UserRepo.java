package com.softwaredesign.assignment2.persistance.repo;

import com.softwaredesign.assignment2.persistance.entity.Role;
import com.softwaredesign.assignment2.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {

    public List<User> findAllByUsername(String username);

    public  List<User> findAllByRole(Role role);

}
