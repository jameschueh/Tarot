package com.example.demo.entity;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "cardlog")
public class CardLogEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @Column
    private String card1;

    @Column
    private Boolean card1updown;

    @Column
    private String card2;

    @Column
    private Boolean card2updown;

    @Column
    private String card3;

    @Column
    private Boolean card3updown;

    @Column
    private String card4;

    @Column
    private Boolean card4updown;

    @Column
    private String card5;

    @Column
    private Boolean card5updown;

    @Column(nullable = false)
    private String mark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCard1() {
        return card1;
    }

    public void setCard1(String card1) {
        this.card1 = card1;
    }

    public Boolean getCard1updown() {
        return card1updown;
    }

    public void setCard1updown(Boolean card1updown) {
        this.card1updown = card1updown;
    }

    public String getCard2() {
        return card2;
    }

    public void setCard2(String card2) {
        this.card2 = card2;
    }

    public Boolean getCard2updown() {
        return card2updown;
    }

    public void setCard2updown(Boolean card2updown) {
        this.card2updown = card2updown;
    }
    
    public String getCard3() {
        return card3;
    }

    public void setCard3(String card3) {
        this.card3 = card3;
    }

    public Boolean getCard3updown() {
        return card3updown;
    }

    public void setCard3updown(Boolean card3updown) {
        this.card3updown = card3updown;
    }
    
    public String getCard4() {
        return card4;
    }

    public void setCard4(String card4) {
        this.card4 = card4;
    }

    public Boolean getCard4updown() {
        return card4updown;
    }

    public void setCard4updown(Boolean card4updown) {
        this.card4updown = card4updown;
    }
    
    public String getCard5() {
        return card5;
    }

    public void setCard5(String card5) {
        this.card5 = card5;
    }

    public Boolean getCard5updown() {
        return card5updown;
    }

    public void setCard5updown(Boolean card5updown) {
        this.card5updown = card5updown;
    }
    
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
