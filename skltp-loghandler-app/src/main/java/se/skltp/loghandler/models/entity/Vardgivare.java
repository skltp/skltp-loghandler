package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@Table(name = "vardgivare")
@NamedQueries({ @NamedQuery(name = "VardgivareByName", query = "SELECT o FROM Vardgivare o where o.name=:name"),
        @NamedQuery(name = "VardgivareById", query = "SELECT o FROM Vardgivare o where o.id=:id")})
public class Vardgivare extends AnslutningChild {

}
