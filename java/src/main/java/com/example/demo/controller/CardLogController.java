package com.example.demo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CardLogEntity;
import com.example.demo.service.CardLogService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CardLogController {
    @Autowired
    private CardLogService cardLogService;

    // get all
    @GetMapping("/CardLog")
    public ResponseEntity<Iterable<CardLogEntity>> getCardLog() {
        Iterable<CardLogEntity> cardLog = cardLogService.getCardLog();
        return ResponseEntity.status(HttpStatus.OK).body(cardLog);
    }

    // get id
    @GetMapping("/CardLog/{id}")
    public Optional<CardLogEntity> getTodo(@PathVariable Integer id) {
        Optional<CardLogEntity> cardLog = cardLogService.findCardLogById(id);
        return cardLog;
    }

    // create 
    @PostMapping("/CardLog")
    public ResponseEntity<Integer> createCardLog(@RequestBody CardLogEntity cardLog) {
        Integer rlt = cardLogService.createCardLog(cardLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(rlt);
    }

    // update id
    @PatchMapping("/CardLog/{id}")
    public ResponseEntity<String> updateCardLog(@PathVariable Integer id,@RequestBody CardLogEntity cardLog) {
        Boolean result = cardLogService.updateCardLog(id, cardLog);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("更新成功");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("更新失败");
        }
    }

    // delete id
    @DeleteMapping("/CardLog/{id}")
    public ResponseEntity<String> deleteCardLog(@PathVariable Integer id) {
        //ResponseEntity<String>補<String>修正ResponseEntity is a raw type. References to generic type ResponseEntity<T> should be parameterized提示訊息
        Boolean rlt = cardLogService.deleteCardLog(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }
}
