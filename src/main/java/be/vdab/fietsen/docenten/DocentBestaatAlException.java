package be.vdab.fietsen.docenten;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DocentBestaatAlException extends RuntimeException {
    public DocentBestaatAlException() {
        super("Docent bestaat al.");

    }
}
