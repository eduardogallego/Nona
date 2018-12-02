package edu.nona.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Bracelet implements Serializable {

    private @Id
    String id;
    private String hardwareVersion;
    private String firmwareVersion;
    @NotNull
    private int pollingInterval;    // in seconds
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date keepalive;
    @ManyToMany(mappedBy = "braceletList")
    @JsonIgnore
    private List<Carer> carerList;
    @OneToMany(mappedBy = "bracelet")
    @JsonIgnore
    private List<Poll> pollList;

    protected Bracelet() {
    }

    public Bracelet(String id, String hardwareVersion, String firmwareVersion, int pollingInterval) {
        this.id = id;
        this.hardwareVersion = hardwareVersion;
        this.firmwareVersion = firmwareVersion;
        this.pollingInterval = pollingInterval;
    }

    public String getId() {
        return id;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public int getPollingInterval() {
        return pollingInterval;
    }

    public Date getCreation() {
        return creation;
    }

    public Date getKeepalive() {
        return keepalive;
    }

    public List<Carer> getCarerList() {
        return carerList;
    }

    public List<Poll> getPollList() {
        return pollList;
    }

    @Override
    public String toString() {
        return "Bracelet{" +
                "id='" + id + '\'' +
                ", hardwareVersion='" + hardwareVersion + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", pollingInterval=" + pollingInterval +
                ", creation=" + creation +
                ", keepalive=" + keepalive +
                '}';
    }
}
