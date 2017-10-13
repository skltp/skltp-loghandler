package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@NamedQueries({ @NamedQuery(name = "OrganisatoriskenhetByName", query = "SELECT o FROM Organisatoriskenhet o where o.name=:name")})
public class Organisatoriskenhet extends AnslutningChild {

}
