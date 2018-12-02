package edu.nona.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Smartphone implements Serializable {

    private @Id
    String id;
    @NotNull
    @ManyToOne
    @JoinColumn(referencedColumnName = "username")
    @JsonIgnore
    private Carer carer;
    private String manufacturer;
    private String model;
    private String os;
    private String osVersion;
    private String pushToken;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date keepalive;

    protected Smartphone() {
    }

    public Smartphone(String id, Carer carer, String manufacturer, String model,
                      String os, String osVersion, String pushToken) {
        this.id = id;
        this.carer = carer;
        this.manufacturer = manufacturer;
        this.model = model;
        this.os = os;
        this.osVersion = osVersion;
        this.pushToken = pushToken;
    }

    public String getId() {
        return id;
    }

    public Carer getCarer() {
        return carer;
    }

    @Transient
    public String getCarerId() {
        return carer.getUsername();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getOs() {
        return os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getPushToken() {
        return pushToken;
    }

    public Date getCreation() {
        return creation;
    }

    public Date getKeepalive() {
        return keepalive;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id='" + id + '\'' +
                ", carer='" + carer + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", os='" + os + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", pushToken='" + pushToken + '\'' +
                ", creation=" + creation +
                ", keepalive=" + keepalive +
                '}';
    }
}
