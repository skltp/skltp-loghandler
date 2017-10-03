package se.skltp.loghandler.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
public class Kallsystem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String kallsystem;

    public long getId() {
        return id;
    }

    public String getKallsystem() {
        return kallsystem;
    }

    public void setKallsystem(String kallsystem) {
        this.kallsystem = kallsystem;
    }
}
