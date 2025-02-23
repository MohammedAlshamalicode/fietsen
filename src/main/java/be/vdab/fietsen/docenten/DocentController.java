package be.vdab.fietsen.docenten;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("docenten")
class DocentController {
    private final DocentService docentService;

    public DocentController(DocentService docentService) {
        this.docentService = docentService;
    }

    @GetMapping("aantal")
    long findAantal() {
        return docentService.findAantal();
    }
    @GetMapping
    List<Docent> findAll() {
        return docentService.findAll();
    }
    @GetMapping("{id}")
    Docent findById(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new);
    }
    @GetMapping("{id}/bestaat")
    boolean bestaatById(@PathVariable long id) {
        return docentService.existsById(id);
    }

    @PostMapping long create(@RequestBody @Valid NieuweDocent nieuweDocent) {
        return docentService.create(nieuweDocent);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        try {
            docentService.delete(id);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

//    @GetMapping(params = "wedde")
//    List<Docent> findByWedde(@RequestParam BigDecimal wedde) {
//        return docentService.findByWedde(wedde);
//    }

    @GetMapping(params = "wedde")
    List<Docent> findByWeddeOrderByFamilienaam(@RequestParam BigDecimal wedde) {
        return docentService.findByWeddeOrderByFamilienaam(wedde);
    }

    @GetMapping(params = "emailAdres")
    Docent findByEmailAdres(@RequestParam String emailAdres) {
        return docentService.findByEmailAdres(emailAdres)
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping(value="aantal", params="wedde")
    int findAantalMetWedde(BigDecimal wedde) {
        return docentService.findAantalMetWedde(wedde);
    }

    @GetMapping("metGrootsteWedde")
    List<Docent> findMetGrootsteWedde() {
        return docentService.findMetGrootsteWedde();
    }

    @GetMapping("weddes/grootste")
    BigDecimal findGrootsteWedde() {
        return docentService.findGrootsteWedde();
    }
    @GetMapping("namen")
    List<EnkelNaam> findNamen() {
        return docentService.findNamen();
    }
    @GetMapping("aantalPerWedde")
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return docentService.findAantalDocentenPerWedde();
    }

    @PutMapping("{id}/wedde")
    void wijzigWedde(@PathVariable long id,
                     @RequestBody @NotNull @Positive BigDecimal wedde){
//        docentService.wijzigWedde(id, wedde);
        //تعديل لاستخدام optimistic locking
        try {
            docentService.wijzigWedde(id, wedde);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new EenAndereGebruikerWijzigdeDeDocentException();
        }
    }

    @PostMapping("weddeverhogingen")
    void algemeneOpslag(@RequestBody @NotNull @Positive BigDecimal bedrag) {
        docentService.algemeneOpslag(bedrag);
    }

    @PostMapping("{id}/bijnamen")
    void voegBijnaamToe(@PathVariable long id,
                        @RequestBody @NotBlank String bijnaam) {
        docentService.voegBijnaamToe(id, bijnaam);
    }
    @DeleteMapping("{id}/bijnamen/{bijnaam}")
    void verwijderBijnaam(@PathVariable long id, @PathVariable String bijnaam) {
        docentService.verwijderBijnaam(id, bijnaam);
    }

    @GetMapping("{id}/emailAdres")
    String findEmailAdresById(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .getEmailAdres();
    }


    //ٌRecords
    private record Opslag(@NotNull @Positive BigDecimal bedrag) {}
}