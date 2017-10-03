package se.skltp.loghandler.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
public class Vardenhet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String vardenhet;

    public long getId() {
        return id;
    }

    public String getVardenhet() {
        return vardenhet;
    }

    public void setVardenhet(String vardenhet) {
        this.vardenhet = vardenhet;
    }
}
