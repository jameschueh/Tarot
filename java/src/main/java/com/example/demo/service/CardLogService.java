package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CardLogEntity;
import com.example.demo.repository.CardLogRepository;

@Service
public class CardLogService {

    @Autowired
    private CardLogRepository cardLogRepository;

    // 查詢所有CardLog紀錄
    public Iterable<CardLogEntity> getCardLog() {
        return cardLogRepository.findAll();
    }

    // 查詢單一CardLog紀錄
    public Integer createCardLog(CardLogEntity cardLog) {
        CardLogEntity rltcardlog = cardLogRepository.save(cardLog);
        return rltcardlog.getId();
    }

    // 新增或更新CardLog紀錄
    public Boolean updateCardLog(Integer id, CardLogEntity updatedCardLog) {
        // 從資料庫獲取對應的 CardLogEntity 對象
        Optional<CardLogEntity> cardLog = findCardLogById(id);
        
        if (cardLog.isPresent()) {
            CardLogEntity existingCardLog = cardLog.get();

            existingCardLog.setDate(updatedCardLog.getDate());
            existingCardLog.setMark(updatedCardLog.getMark());
            // 保存
            cardLogRepository.save(existingCardLog);

            return true;
        } else {
            return false;
        }
    }

    // 從資料庫獲取對應的 CardLogEntity 對象
    public Optional<CardLogEntity> findCardLogById(Integer id) {
        Optional<CardLogEntity> cardLogRepository = this.cardLogRepository.findById(id);
        return cardLogRepository;
    }

    // 刪除CardLog紀錄
    public Boolean deleteCardLog(Integer id) {
        Optional<CardLogEntity> findCardLog = findCardLogById(id);
        if (!findCardLog.isPresent()) {
            return false;
        }
        cardLogRepository.deleteById(id);
        return true;
    }
}
