package com.PortfolioApp.Stocks_portfolio.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;



@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "user")
    List<StockHolding> holdingList = new ArrayList<>();
    public UserEntity(){

    }
    public UserEntity(int id, String name, String email, List<StockHolding> holdingList){
        this.name = name;
        this.email =email;
        this.id = id;
        this.holdingList = holdingList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<StockHolding> getHoldingList() {
        return holdingList;
    }

    public void setHoldingList(List<StockHolding> holdingList) {
        this.holdingList = holdingList;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id+
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", holdingList=" + holdingList +
                '}';
    }
}
