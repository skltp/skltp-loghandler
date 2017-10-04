package se.skltp.loghandler.models;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@NamedQueries({ @NamedQuery(name = "KallsystemByHSAId", query = "SELECT o FROM Kallsystem o where o.hsaid=:hsaid")})
public class Kallsystem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String hsaid;

    public long getId() {
        return id;
    }

    public String getHsaid() {
        return hsaid;
    }

    public void setHsaid(String hsaid) {
        this.hsaid = hsaid;
    }
}
