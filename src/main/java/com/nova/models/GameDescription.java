package com.nova.models;

import com.nova.models.enums.GBMB;
import com.nova.models.enums.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GameDescription {
    @Id
    private long gameid;

    private String dev, pub, os, proc, videocard, sound, description, file;

    private int ram, place;

    private float version;
    private String[] screenshots;
    private GBMB switchram, switchplace;
    private Language language;

    public GameDescription(
            String dev, String pub, String os, String proc,
            String videocard, String sound, String file,
            int ram, int place, float version,
            GBMB switchram, GBMB switchplace, Language language
    ) {
        this.dev = dev;
        this.pub = pub;
        this.os = os;
        this.proc = proc;
        this.videocard = videocard;
        this.sound = sound;
        this.file = file;
        this.ram = ram;
        this.place = place;
        this.version = version;
        this.switchram = switchram;
        this.switchplace = switchplace;
        this.language = language;
    }

}
