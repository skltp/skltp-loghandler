package se.skltp.loghandler.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
public class Tjanstekontrakt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tjanstekontrakt;

    public long getId() {
        return id;
    }

    public String getTjanstekontrakt() {
        return tjanstekontrakt;
    }

    public void setTjanstekontrakt(String tjanstekontrakt) {
        this.tjanstekontrakt = tjanstekontrakt;
    }
}
