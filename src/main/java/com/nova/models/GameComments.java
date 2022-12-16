package com.nova.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GameComments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long gameid;
    private String username;
    private String date;
    private String comment;

    public GameComments(long gameid, String username, String date, String comment) {
        this.gameid = gameid;
        this.username = username;
        this.date = date;
        this.comment = comment;
    }

}
