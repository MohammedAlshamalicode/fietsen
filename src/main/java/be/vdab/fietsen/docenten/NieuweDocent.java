package be.vdab.fietsen.docenten;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

record NieuweDocent(
        @NotBlank String voornaam,
        @NotBlank String familienaam,
        @NotNull @PositiveOrZero BigDecimal wedde,
        @NotNull @Email String emailAdres,
        @NotNull Geslacht geslacht) {
}
