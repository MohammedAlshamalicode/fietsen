package be.vdab.fietsen.campussen;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "campussen")
public class Campus {
    @Id
    private long id;
    private String naam;

    @Embedded
    private Adres adres;

    public Campus(long id, String naam, Adres adres) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
    }

    protected Campus() {}

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }


// constructor met parameters. Je gebruikt die constructor later.
// default protected constructor die JPA nodig heeft voor intern gebruik
// getters
}