package be.vdab.fietsen.docenten;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DocentRepository extends JpaRepository<Docent,Long> {

    // البحث عن المدرسين الذين يتقاضون راتب يساوي القيمة
    List<Docent> findByWedde(BigDecimal wedde);

    List<Docent> findByWeddeOrderByFamilienaam(BigDecimal wedde);
    // البحث عن المدرسين الذين يتقاضون راتبا مختلفا عن المبلغ المحدد
   List<Docent> findByWeddeIsNot(BigDecimal wedde);
    // البحث عن المدرسين الذين يتقاضون راتبا أعلى من المبلغ المحدد
   List<Docent> findByWeddeGreaterThan(BigDecimal bedrag);
    // البحث عن المدرسين الذين يتقاضون راتبا يساوي أو أعلى من المبلغ المحدد
   List<Docent> findByWeddeGreaterThanEqual(BigDecimal bedrag);
    // البحث عن المدرسين الذين يتقاضون راتبا ضمن نطاق معين
   List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot);
    //البحث عن الموردين الذين لا يحتوي راتبهم على قيمة
   List<Docent> findByWeddeIsNull();
    // البحث عن المدرسين الذين يبدأ اسم عائلتهم بحروف معين
   List<Docent> findByFamilienaamStartingWith(String woord);
    // البحث عن المدرسين الذين يحتوي اسم عائلتهم على حروف معينة
   List<Docent> findByFamilienaamContaining(String woord);
    // البحث عن المدرسين الذين يطابق اسمهم واسم العائلة القيم المحددة
   List<Docent> findByFamilienaamAndVoornaam(String familienaam, String voornaam);

   Optional<Docent> findByEmailAdres(String emailAdres);

    int countByWedde(BigDecimal wedde);

    boolean existsByEmailAdres(String emailAdres);

    boolean existsByWeddeGreaterThan(BigDecimal vanaf);

    void deleteByEmailAdres(String emailAdres);

    void deleteByWeddeGreaterThan(BigDecimal vanaf);
}
