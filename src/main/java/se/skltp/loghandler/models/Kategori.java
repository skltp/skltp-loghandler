package se.skltp.loghandler.models;

import javax.persistence.*;

/**
 * Created by parlin on 2017-10-03.
 */
@Entity
@NamedQueries({ @NamedQuery(name = "KategoriByKategori", query = "SELECT o FROM Kategori o where o.kategori=:kategori")})
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String kategori;

    public long getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
