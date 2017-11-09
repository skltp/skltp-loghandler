package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@Table(name = "vardenhet")
@NamedQueries({ @NamedQuery(name = "VardenhetByName", query = "SELECT o FROM Vardenhet o where o.name=:name"),
        @NamedQuery(name = "VardenhetById", query = "SELECT o FROM Vardenhet o where o.id=:id")})
public class Vardenhet extends AnslutningChild {

}
