package Rühmatöö;

import java.time.LocalDateTime;

public class TehingudTest {

    public static void main(String[] args) {
        // Loome mõned tehingud testimiseks
        Tehingud tehingud = new Tehingud();
        tehingud.lisaTehing(Konto.getKasutajaNimi(), "Kasutaja2", "Makse", 50.0, LocalDateTime.of(2024, 2, 20, 10, 15));
        tehingud.lisaTehing(Konto.getKasutajaNimi(), "Kasutaja3", "Makse", 100.0, LocalDateTime.of(2024, 2, 24, 8, 30));
        tehingud.lisaTehing(Konto.getKasutajaNimi(), "Kasutaja1", "Makse", 30.0, LocalDateTime.of(2024, 2, 22, 15, 45));
        tehingud.lisaTehing(Konto.getKasutajaNimi(), "Kasutaja1", "Makse", 70.0, LocalDateTime.of(2024, 2, 23, 12, 0));

        // Väljastame tehingute ajaloo
        System.out.println("Tehingute ajalugu:");
        tehingud.kuvaTehinguteAjalugu();

        // Kirjutame tehingute ajaloo faili
        tehingud.kirjutaFaili("tehingute_ajalugu.txt");
    }
}
