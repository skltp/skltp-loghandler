package se.skltp.loghandler.models.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "anslutning", indexes = { @Index(name = "IDX_ANSLUTNING", columnList = "vardgivare,vardenhet,organisatoriskenhet,tjanstekontrakt,kategori,kallsystem,ursprungligkonsument", unique = true) })
public class Anslutning {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
