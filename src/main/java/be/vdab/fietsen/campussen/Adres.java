package be.vdab.fietsen.campussen;

import jakarta.persistence.Embeddable;


//@Embeddable staat voor een value object class of een value object record.
@Embeddable // يمكن تضمينه في كائنات اخرى
public record Adres(String straat, String huisNr, int postcode, String gemeente){}