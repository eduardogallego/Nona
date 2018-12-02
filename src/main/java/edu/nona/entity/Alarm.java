package edu.nona.entity;

import edu.nona.enums.AlarmType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Alarm implements Serializable {

    private @Id
    @GeneratedValue
    long id;
    @NotNull
    private AlarmType type;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @NotNull
    private String message;

    protected Alarm() {
    }

    public Alarm(AlarmType type, String message) {
        this.type = type;
        this.message = message;
        this.timestamp = new Date();
    }

    public long getId() {
        return id;
    }

    public AlarmType getType() {
        return type;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
