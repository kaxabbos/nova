package com.nova.models;

import com.nova.models.enums.Genre;
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
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    main
    private long userid;
    private String name, poster;
    private int year;
    private float price;
    private Genre genre;

    public Games(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }

}
