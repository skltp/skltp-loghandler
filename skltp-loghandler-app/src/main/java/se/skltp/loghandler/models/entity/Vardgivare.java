package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@NamedQueries({ @NamedQuery(name = "VardgivareByName", query = "SELECT o FROM Vardgivare o where o.name=:name")})
public class Vardgivare extends AnslutningChild {

}
