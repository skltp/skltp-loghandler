package se.skltp.loghandler.models.entity;

import org.springframework.cglib.core.HashCodeCustomizer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "anslutning", indexes = { @Index(name = "IDX_ANSLUTNING", columnList = "vardgivare,vardenhet,organisatoriskenhet,tjanstekontrakt,kategori,kallsystem,ursprungligkonsument", unique = true) })
public class Anslutning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "vardgivare")
    private Vardgivare vardgivare;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "vardenhet")
    private Vardenhet vardenhet;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "organisatoriskenhet")
    private Organisatoriskenhet organisatoriskenhet;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "tjanstekontrakt")
    private Tjanstekontrakt tjanstekontrakt;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "kategori")
    private Kategori kategori;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "kallsystem")
    private Kallsystem kallsystem;

    private Date oldest;

    private Date youngest;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ursprungligkonsument")
    private Ursprungligkonsument ursprungligkonsument;

    public Vardgivare getVardgivare() {
        return vardgivare;
    }

    public void setVardgivare(Vardgivare vardgivare) {
        this.vardgivare = vardgivare;
    }

    public Vardenhet getVardenhet() {
        return vardenhet;
    }

    public void setVardenhet(Vardenhet vardenhet) {
        this.vardenhet = vardenhet;
    }

    public Organisatoriskenhet getOrganisatoriskenhet() {
        return organisatoriskenhet;
    }

    public void setOrganisatoriskenhet(Organisatoriskenhet organisatoriskenhet) {
        this.organisatoriskenhet = organisatoriskenhet;
    }

    public Tjanstekontrakt getTjanstekontrakt() {
        return tjanstekontrakt;
    }

    public void setTjanstekontrakt(Tjanstekontrakt tjanstekontrakt) {
        this.tjanstekontrakt = tjanstekontrakt;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Kallsystem getKallsystem() {
        return kallsystem;
    }

    public void setKallsystem(Kallsystem kallsystem) {
        this.kallsystem = kallsystem;
    }

    public Date getOldest() {
        return oldest;
    }

    public void setOldest(Date oldest) {
        this.oldest = oldest;
    }

    public Date getYoungest() {
        return youngest;
    }

    public void setYoungest(Date youngest) {
        this.youngest = youngest;
    }

    public Ursprungligkonsument getUrsprungligkonsument() {
        return ursprungligkonsument;
    }

    public void setUrsprungligkonsument(Ursprungligkonsument ursprungligkonsument) {
        this.ursprungligkonsument = ursprungligkonsument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anslutning that = (Anslutning) o;

        if (vardgivare != null ? !vardgivare.equals(that.vardgivare) : that.vardgivare != null) return false;
        if (vardenhet != null ? !vardenhet.equals(that.vardenhet) : that.vardenhet != null) return false;
        if (organisatoriskenhet != null ? !organisatoriskenhet.equals(that.organisatoriskenhet) : that.organisatoriskenhet != null)
            return false;
        if (tjanstekontrakt != null ? !tjanstekontrakt.equals(that.tjanstekontrakt) : that.tjanstekontrakt != null)
            return false;
        if (kategori != null ? !kategori.equals(that.kategori) : that.kategori != null) return false;
        if (kallsystem != null ? !kallsystem.equals(that.kallsystem) : that.kallsystem != null) return false;
        return ursprungligkonsument != null ? ursprungligkonsument.equals(that.ursprungligkonsument) : that.ursprungligkonsument == null;
    }

    @Override
    public int hashCode() {
        int result = vardgivare != null ? vardgivare.hashCode() : 0;
        result = 31 * result + (vardenhet != null ? vardenhet.hashCode() : 0);
        result = 31 * result + (organisatoriskenhet != null ? organisatoriskenhet.hashCode() : 0);
        result = 31 * result + (tjanstekontrakt != null ? tjanstekontrakt.hashCode() : 0);
        result = 31 * result + (kategori != null ? kategori.hashCode() : 0);
        result = 31 * result + (kallsystem != null ? kallsystem.hashCode() : 0);
        result = 31 * result + (ursprungligkonsument != null ? ursprungligkonsument.hashCode() : 0);
        return result;
    }

}
