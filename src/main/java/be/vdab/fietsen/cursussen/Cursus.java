package be.vdab.fietsen.cursussen;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  //1
//@Inheritance(strategy = InheritanceType.JOINED)   //2
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 3
@Table(name = "cursussen")
//@DiscriminatorColumn(name = "soort")  //1
abstract class Cursus {
    @Id
    private UUID id; //3
    private String naam;

    public UUID getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}