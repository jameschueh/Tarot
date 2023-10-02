package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.example.demo.entity.TarotEntity;
import com.example.demo.service.TarotService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TarotController {
    @Autowired
    private TarotService tarotService;

    @GetMapping("/Tarot")
    public ResponseEntity<Iterable<TarotEntity>> getTarot() {
        // 獲取所有Tarot紀錄
        Iterable<TarotEntity> tarotList = tarotService.getTarot();

        // 設定每個Tarot的圖片URL
        for (TarotEntity tarot : tarotList) {
            tarot.setImg("/api/Tarot/tarotImages/" + tarot.getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarotList);
    }

    @GetMapping("/Tarot/{id}")
    public ResponseEntity<TarotEntity> getTarot(@PathVariable Integer id) {
        Optional<TarotEntity> tarotOptional = tarotService.findTarotById(id);

        if (tarotOptional.isPresent()) {
            TarotEntity tarot = tarotOptional.get();

            // 設定圖片URL
            tarot.setImg("/api/Tarot/tarotImages/" + tarot.getId());

            return ResponseEntity.ok(tarot);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/Tarot/tarotImages/{id}")
    public ResponseEntity<byte[]> getTarotImage(@PathVariable Integer id) {
        try {
            // 從resources目錄中讀取圖片ClassPathResource getInputStream
            Resource resource = new ClassPathResource("static/tarotImages/" + id + ".jpg");
            InputStream inputStream = resource.getInputStream();
            byte[] imageBytes = inputStream.readAllBytes();

            // 返回圖片數據
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (IOException e) {
            // 處理圖片讀取錯誤
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}