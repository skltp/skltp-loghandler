package se.skltp.loghandler.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
public class Ursprungligkonsument {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String ursprungligkonsument;

    public long getId() {
        return id;
    }

    public String getUrsprungligkonsument() {
        return ursprungligkonsument;
    }

    public void setUrsprungligkonsument(String ursprungligkonsument) {
        this.ursprungligkonsument = ursprungligkonsument;
    }
}
