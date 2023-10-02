package com.example.demo.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tarot")
public class TarotEntity {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private int id;
    
    @Column
    private String name_ch;

    @Column
    private String name_en;
    
    @Column
    private String bs;

    @Column
    private String up;

    @Column
    private String down;

    @Column
    private String mean;

    @Column
    private String upmean;

    @Column
    private String downmean;

    @Column
    private String suggest;

    @Column
    private String img;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName_ch() {
        return name_ch;
    }

    public void setName_ch(String name_ch) {
        this.name_ch = name_ch;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }
    
    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getUpmean() {
        return upmean;
    }

    public void setUpmean(String upmean) {
        this.upmean = upmean;
    }    
    
    public String getDownmean() {
        return downmean;
    }

    public void setDownmean(String upmean) {
        this.upmean = upmean;
    }    

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    } 

    public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
