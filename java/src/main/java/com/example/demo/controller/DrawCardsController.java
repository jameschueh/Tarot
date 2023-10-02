package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TarotEntity;
import com.example.demo.service.TarotService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DrawCardsController {

    @Autowired
    private TarotService tarotService;
    
    // 從前端接收抽取卡牌的數量
    @GetMapping("/Drawcards")
    public ResponseEntity<List<Map<String, Object>>> drawCards(@RequestParam("count") int count) {

        // 卡牌數量大於0
        if (count <= 0) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        // 獲取卡牌資料
        Iterable<TarotEntity> allTarotCards = tarotService.getTarot();
        List<TarotEntity> tarotList = new ArrayList<>();
        allTarotCards.forEach(tarotList::add);

        // 判斷卡牌數量大於可用卡牌數量
        if (count > tarotList.size()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        // 存取抽到的卡牌資料
        List<Map<String, Object>> drawnCards = new ArrayList<>();
        Random random = new Random();
        Set<Integer> drawnIndexes = new HashSet<>();

        // 不重複隨機抽牌、判斷正反面
        while (drawnCards.size() < count) {
            int randomIndex = random.nextInt(tarotList.size());
            if (drawnIndexes.add(randomIndex)) {
                TarotEntity tarot = tarotList.get(randomIndex);
                boolean reversed = random.nextBoolean();

                // 根據卡牌ID 獲取TarotEntity的詳細資料
                Optional<TarotEntity> tarotEntityOptional = tarotService.findTarotById(tarot.getId());
                TarotEntity tarotEntity = tarotEntityOptional.orElse(new TarotEntity());

                Map<String, Object> cardInfo = new HashMap<>();
                cardInfo.put("id", tarot.getId());
                cardInfo.put("name_ch", tarot.getName_ch());
                cardInfo.put("name_en", tarot.getName_en());
                cardInfo.put("updown", reversed ? "down" : "up");
                cardInfo.put("updownmean", reversed ? tarotEntity.getDown() : tarotEntity.getUp());
                cardInfo.put("updownmeandetail", reversed ? tarotEntity.getDownmean() : tarotEntity.getUpmean());
                cardInfo.put("img", tarot.getImg());

                drawnCards.add(cardInfo);
            }
        }

        return ResponseEntity.ok(drawnCards);
    }
}
