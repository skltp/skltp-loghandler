package se.skltp.loghandler.models.entity;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@NamedQueries({ @NamedQuery(name = "KategoriByName", query = "SELECT o FROM Kategori o where o.name=:name")})
public class Kategori extends AnslutningChild {

}
