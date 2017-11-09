package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@Table(name = "kallsystem")
@NamedQueries({ @NamedQuery(name = "KallsystemByName", query = "SELECT o FROM Kallsystem o where o.name=:name"),
        @NamedQuery(name = "KallsystemById", query = "SELECT o FROM Kallsystem o where o.id=:id")})
public class Kallsystem extends AnslutningChild {

}
