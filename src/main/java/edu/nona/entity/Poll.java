package edu.nona.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Poll implements Serializable {

    private @Id
    @GeneratedValue
    long id;
    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Bracelet bracelet;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date timestamp;
    private Integer wristTemp;
    private Integer roomTemp;
    private Long latitude;
    private Long longitude;
    private Integer pulse;
    private Integer battery;

    protected Poll() {
    }

    public Poll(Bracelet bracelet, Integer wristTemp, Integer roomTemp, Long latitude,
                Long longitude, Integer pulse, Integer battery) {
        this.bracelet = bracelet;
        this.timestamp = new Date();
        this.wristTemp = wristTemp;
        this.roomTemp = roomTemp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pulse = pulse;
        this.battery = battery;
    }

    public long getId() {
        return id;
    }

    public Bracelet getBracelet() {
        return bracelet;
    }

    @Transient
    public String getBraceletId() {
        return bracelet.getId();
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getWristTemp() {
        return wristTemp;
    }

    public Integer getRoomTemp() {
        return roomTemp;
    }

    public Long getLatitude() {
        return latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public Integer getPulse() {
        return pulse;
    }

    public Integer getBattery() {
        return battery;
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", bracelet=" + bracelet +
                ", timestamp=" + timestamp +
                ", wristTemp=" + wristTemp +
                ", roomTemp=" + roomTemp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pulse=" + pulse +
                ", battery=" + battery +
                '}';
    }
}