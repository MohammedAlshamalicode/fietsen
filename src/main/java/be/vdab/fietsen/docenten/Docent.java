package be.vdab.fietsen.docenten;

import be.vdab.fietsen.campussen.DocentHeeftDezeBijnaamAlException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "docenten")
public class Docent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam ;
    private String familienaam;
    private BigDecimal wedde;
    private String emailAdres;
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;
    @Version
    private int version;
    //20.2 VERZAMELING VALUE OBJECTEN MET EEN BASISTYPE
    @ElementCollection
    @CollectionTable(name = "bijnamen",
    joinColumns = @JoinColumn(name = "docentId") )
    @Column(name = "bijnaam")
    private Set<String> bijnamen;

    public Docent( String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.emailAdres = emailAdres;
        this.geslacht = geslacht;
        bijnamen = new LinkedHashSet<>();
    }

    protected Docent() {}

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public BigDecimal getWedde() {return wedde;}

    public String getEmailAdres() {return emailAdres;}

    public Geslacht getGeslacht() {return geslacht;}

    void setWedde(BigDecimal wedde) {this.wedde = wedde;}

    void voegBijnaamToe(String bijnaam) {
        if ( ! bijnamen.add(bijnaam)) {
            throw new DocentHeeftDezeBijnaamAlException();
        }
    }

    void verwijderBijnaam(String bijnaam) {
        bijnamen.remove(bijnaam);
    }

    public Set<String> getBijnamen() {
//        return bijnamen;
        return Collections.unmodifiableSet(bijnamen);
    }
}
