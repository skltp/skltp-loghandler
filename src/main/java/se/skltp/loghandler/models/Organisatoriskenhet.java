package se.skltp.loghandler.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
public class Organisatoriskenhet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String organisatoriskenhet;

    public long getId() {
        return id;
    }

    public String getOrganisatoriskenhet() {
        return organisatoriskenhet;
    }

    public void setOrganisatoriskenhet(String organisatoriskenhet) {
        this.organisatoriskenhet = organisatoriskenhet;
    }
}
