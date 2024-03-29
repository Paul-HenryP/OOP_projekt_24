package Rühmatöö;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import static Rühmatöö.Tehing.saatja;

/***********************************
 *
 *
 * Kirjeldus: Klass Tehingud talletab tehingute ajalugu ja sialdab meetodeid mille abil uus tehing ajalukku lisada (Kui aega on ss ka kustutamise meetod),
 * tehingute faili kirjutamist (Saaks ka failist lugemist kui jõuab)
 *
 * Autor: Andreas Kelder
 *
 **********************************/

public class Tehingud {

    private static ArrayList<Tehing> tehingud;
    public Tehingud() {
        tehingud = new ArrayList<>();
    }

    // Meetod uue tehingu lisamiseks ajalukku
    public static void lisaTehing(String kasutajaNimi, String saaja, String selgitus, double summa, LocalDateTime kuupäev) { //Paul: Võiks kasutada lihtsalt Tehingu objekti parameetrina.
        Tehing tehing = new Tehing(kasutajaNimi, saaja, selgitus, summa, kuupäev); // Paul: Siis lisada faili tehingu toString() Tulemus.
        tehingud.add(tehing);
    }

    public static ArrayList<Tehing> getKasutajaTehingud(String kasutajanimi) {
        ArrayList<Tehing> kasutajaTehingud = new ArrayList<>();
        for (Tehing tehing : tehingud) {
            if (tehing.getSaatja().equals(kasutajanimi)) {
                kasutajaTehingud.add(tehing);
            }
        }
        return kasutajaTehingud;
    }



    // Meetod tehingute saamiseks
    public static ArrayList<Tehing> getTehinguteAjalugu() {
        return tehingud;
    }

    // Tehingute ajaloo kuvamise funktsioon
    public void kuvaTehinguteAjalugu() {
        for (Tehing tehing : tehingud) {
            //Paul: Siia lihtsalt toString meetodi väljakutse. 1 reaga.
            System.out.println("** Tehingu kirjeldus **");
            System.out.println("Saatja: " + Konto.getKasutajaNimi());
            System.out.println("Saaja: " + Tehing.getSaaja());
            System.out.println("Selgitus: " + Tehing.getSelgitus());
            System.out.println("Summa: " + Tehing.getSumma());
            System.out.println("Kuupäev: " + Tehing.getKuupäev());
            System.out.println();

        }
    }
    // Meetod tehingute faili kirjutamiseks (Kui tahab)
    public static void kirjutaFaili(String failinimi){
        try {
            FileWriter fileWriter = new FileWriter(new File(failinimi), true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

            //Tehingu info faili salvestamine
            for (Tehing tehing: tehingud) {
                //Paul: Siia samuti 1 reaga tostring väljakutse.
                printWriter.println("** Tehingu kirjeldus **");
                printWriter.println("Saatja: " + Konto.getKasutajaNimi());
                printWriter.println("Saaja: " + tehing.getSaaja());
                printWriter.println("Selgitus: " + tehing.getSelgitus());
                printWriter.println("Summa: " + tehing.getSumma());
                printWriter.println("Kuupäev: " + formatter.format(tehing.getKuupäev()));
                printWriter.println();
            }
            printWriter.close();
            System.out.println("Tehingu info on salvestatud faili " + failinimi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Meetod tehingute failist lugemiseks (Kui tahta)
    public static void loeFaili(String failinimi) {
        try {
            File file = new File(failinimi);
            BufferedReader reader = new BufferedReader(new FileReader(failinimi));
            String line;
            ArrayList<Tehing> tehingud = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.equals("** Tehingu kirjeldus **")) {
                    // Loetakse failist iga tehingu kirjeldus
                    String saatja = reader.readLine().substring(8); // "Saatja: " jäetakse välja
                    String saaja = reader.readLine().substring(7); // "Saaja: " jäetakse välja
                    String selgitus = reader.readLine().substring(10); // "Selgitus: " jäetakse välja
                    double summa = Double.parseDouble(reader.readLine().substring(7)); // "Summa: " jäetakse välja
                    LocalDateTime kuupäev = LocalDateTime.parse(reader.readLine().substring(9), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")); // "Kuupäev: " jäetakse välja
                    reader.readLine(); // Tühirida

                    // Lisatakse tehing tehingute listi
                    tehingud.add(new Tehing(saatja, saaja, selgitus, summa, kuupäev));
                }
            }
            reader.close();

            // Sorteeritakse tehingud kuupäeva järgi
            Collections.sort(tehingud, (t1, t2) -> t2.getKuupäev().compareTo(t1.getKuupäev()));

            // Väljastatakse tehingud
            for (Tehing tehing : tehingud) {
                System.out.println("** Tehingu kirjeldus **");
                System.out.println("Saatja: " + Konto.getKasutajaNimi());
                System.out.println("Saaja: " + tehing.getSaaja());
                System.out.println("Selgitus: " + tehing.getSelgitus());
                System.out.println("Summa: " + tehing.getSumma());
                System.out.println("Kuupäev: " + DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format(tehing.getKuupäev()));
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Faili ei leitud. ");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String kuvaTehingudKasutajanimega(String kasutajanimi){
        StringBuilder sb = new StringBuilder();
        for(Tehing tehing : tehingud){
            if (Konto.getKasutajaNimi().equals(kasutajanimi)){
                sb.append(tehing).append("\n");
            }
        }
        return sb.toString();
    }

}
