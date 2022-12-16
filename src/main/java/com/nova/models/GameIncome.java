package com.nova.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GameIncome {
    @Id
    private long gameid;

    private long userid;
    private String gamename;
    private float income, price;
    private int count;

    public GameIncome(String gamename, float price) {
        this.gamename = gamename;
        this.price = price;
        this.income = 0;
        this.count = 0;
    }

}
