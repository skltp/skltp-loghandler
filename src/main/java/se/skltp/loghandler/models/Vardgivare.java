package se.skltp.loghandler.models;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
public class Vardgivare {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String vardgivare;

    public long getId() {
        return id;
    }

    public String getVardgivare() {
        return vardgivare;
    }

    public void setVardgivare(String vardgivare) {
        this.vardgivare = vardgivare;
    }
}
