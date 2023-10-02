package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.CardLogEntity;

public interface CardLogRepository extends CrudRepository<CardLogEntity, Integer> {
    
}

