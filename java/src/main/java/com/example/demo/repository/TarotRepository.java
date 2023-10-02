package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.TarotEntity;

public interface TarotRepository extends CrudRepository<TarotEntity, Integer> {
    
}

