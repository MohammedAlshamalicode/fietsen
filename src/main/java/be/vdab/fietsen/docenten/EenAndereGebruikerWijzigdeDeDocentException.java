package be.vdab.fietsen.docenten;

 class EenAndereGebruikerWijzigdeDeDocentException extends RuntimeException {
     EenAndereGebruikerWijzigdeDeDocentException( ) {
        super("Een andere gebruiker wijzigde de docent.");
    }
}
