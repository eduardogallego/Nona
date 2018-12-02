package edu.nona.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Carer implements Serializable {

    private @Id
    String username;
    @NotNull
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date creation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date keepalive;
    @ManyToMany
    @JoinTable(name = "carer2bracelet",
            joinColumns = @JoinColumn(name = "carer", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "bracelet", referencedColumnName = "id"))
    @JsonIgnore
    private List<Bracelet> braceletList;
    @OneToMany(mappedBy = "carer")
    @JsonIgnore
    private List<Smartphone> smartphoneList;

    protected Carer() {
    }

    public Carer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreation() {
        return creation;
    }

    public Date getKeepalive() {
        return keepalive;
    }

    public List<Bracelet> getBraceletList() {
        return braceletList;
    }

    public List<Smartphone> getSmartphoneList() {
        return smartphoneList;
    }

    @Override
    public String toString() {
        return "Carer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creation=" + creation +
                ", keepalive=" + keepalive +
                '}';
    }
}
