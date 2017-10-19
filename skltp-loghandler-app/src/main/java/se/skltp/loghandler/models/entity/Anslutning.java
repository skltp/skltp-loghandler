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

    private long vardgivare;

    private long vardenhet;

    private long organisatoriskenhet;

    private long tjanstekontrakt;

    private long kategori;

    private long kallsystem;

    private Date oldest;

    private Date youngest;

    private long ursprungligkonsument;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVardgivare() {
        return vardgivare;
    }

    public void setVardgivare(long vardgivare) {
        this.vardgivare = vardgivare;
    }

    public long getVardenhet() {
        return vardenhet;
    }

    public void setVardenhet(long vardenhet) {
        this.vardenhet = vardenhet;
    }

    public long getOrganisatoriskenhet() {
        return organisatoriskenhet;
    }

    public void setOrganisatoriskenhet(long organisatoriskenhet) {
        this.organisatoriskenhet = organisatoriskenhet;
    }

    public long getTjanstekontrakt() {
        return tjanstekontrakt;
    }

    public void setTjanstekontrakt(long tjanstekontrakt) {
        this.tjanstekontrakt = tjanstekontrakt;
    }

    public long getKategori() {
        return kategori;
    }

    public void setKategori(long kategori) {
        this.kategori = kategori;
    }

    public long getKallsystem() {
        return kallsystem;
    }

    public void setKallsystem(long kallsystem) {
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

    public long getUrsprungligkonsument() {
        return ursprungligkonsument;
    }

    public void setUrsprungligkonsument(long ursprungligkonsument) {
        this.ursprungligkonsument = ursprungligkonsument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Anslutning that = (Anslutning) o;

        if (vardgivare != that.vardgivare) return false;
        if (vardenhet != that.vardenhet) return false;
        if (organisatoriskenhet != that.organisatoriskenhet) return false;
        if (tjanstekontrakt != that.tjanstekontrakt) return false;
        if (kategori != that.kategori) return false;
        if (kallsystem != that.kallsystem) return false;
        return ursprungligkonsument == that.ursprungligkonsument;
    }

    @Override
    public int hashCode() {
        int result = (int) (vardgivare ^ (vardgivare >>> 32));
        result = 31 * result + (int) (vardenhet ^ (vardenhet >>> 32));
        result = 31 * result + (int) (organisatoriskenhet ^ (organisatoriskenhet >>> 32));
        result = 31 * result + (int) (tjanstekontrakt ^ (tjanstekontrakt >>> 32));
        result = 31 * result + (int) (kategori ^ (kategori >>> 32));
        result = 31 * result + (int) (kallsystem ^ (kallsystem >>> 32));
        result = 31 * result + (int) (ursprungligkonsument ^ (ursprungligkonsument >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Anslutning{" +
                "id=" + id +
                ", vardgivare=" + vardgivare +
                ", vardenhet=" + vardenhet +
                ", organisatoriskenhet=" + organisatoriskenhet +
                ", tjanstekontrakt=" + tjanstekontrakt +
                ", kategori=" + kategori +
                ", kallsystem=" + kallsystem +
                ", oldest=" + oldest +
                ", youngest=" + youngest +
                ", ursprungligkonsument=" + ursprungligkonsument +
                '}';
    }
}
