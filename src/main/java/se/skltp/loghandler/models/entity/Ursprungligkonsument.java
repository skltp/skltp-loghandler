package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@NamedQueries({ @NamedQuery(name = "UrsprungligkonsumentByName", query = "SELECT o FROM Ursprungligkonsument o where o.name=:name")})
public class Ursprungligkonsument extends AnslutningChild {

}
