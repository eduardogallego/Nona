package edu.nona.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Configuration implements Serializable {

    private @Id
    String key;
    @NotNull
    private String value;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date keepalive;

    public Configuration() {
    }

    public Configuration(String key, String value) {
        this.key = key;
        this.value = value;
        Date now = new Date();
        this.creation = now;
        this.keepalive = now;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Date getCreation() {
        return creation;
    }

    public Date getKeepalive() {
        return keepalive;
    }
}
