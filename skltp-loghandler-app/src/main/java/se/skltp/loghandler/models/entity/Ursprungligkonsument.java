package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@NamedQueries({ @NamedQuery(name = "UrsprungligkonsumentByName", query = "SELECT o FROM Ursprungligkonsument o where o.name=:name"),
        @NamedQuery(name = "UrsprungligkonsumentById", query = "SELECT o FROM Ursprungligkonsument o where o.id=:id")})
public class Ursprungligkonsument extends AnslutningChild {

}
