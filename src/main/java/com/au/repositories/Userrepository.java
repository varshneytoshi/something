package com.au.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.au.entities.User;
@Repository
public interface Userrepository extends JpaRepository<User, Integer>{

}
