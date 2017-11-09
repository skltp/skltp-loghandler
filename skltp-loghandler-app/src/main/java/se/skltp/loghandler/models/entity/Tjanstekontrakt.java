package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@Table(name = "tjanstekontrakt")
@NamedQueries({ @NamedQuery(name = "TjanstekontraktByName", query = "SELECT o FROM Tjanstekontrakt o where o.name=:name"),
        @NamedQuery(name = "TjanstekontraktById", query = "SELECT o FROM Tjanstekontrakt o where o.id=:id")})
public class Tjanstekontrakt extends AnslutningChild {

}
