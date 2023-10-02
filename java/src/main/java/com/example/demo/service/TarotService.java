package com.example.demo.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TarotEntity;
import com.example.demo.repository.TarotRepository;

@Service
public class TarotService {

	@Autowired
	private TarotRepository tarotRepository;	

	// 查詢所有Tarot紀錄
    public Iterable<TarotEntity> getTarot() {
        return tarotRepository.findAll();
    }

    // 查詢單一Tarot紀錄	
    public Integer createTarot(TarotEntity tarot) {
		TarotEntity rlttarot = tarotRepository.save(tarot);
        return rlttarot.getId();
    }

    // 獲取對應ID
	public Optional<TarotEntity> findTarotById(Integer id) {
        Optional<TarotEntity> tarotRepository = this.tarotRepository.findById(id);
        return tarotRepository;
    }
}

