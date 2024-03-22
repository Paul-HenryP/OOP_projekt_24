package Rühmatöö;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Comparator;

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

    private ArrayList<Tehing> tehingud;
    public Tehingud() {
        this.tehingud = new ArrayList<>();
    }
    // Meetod uue tehingu lisamiseks ajalukku
    public void lisaTehing(String kasutajaNimi, String saaja, String selgitus, double summa, LocalDateTime kuupäev) {
        Tehing tehing = new Tehing(kasutajaNimi, saaja, selgitus, summa, kuupäev);
        tehingud.add(tehing);
    }
    // Meetod tehingute saamiseks
    public ArrayList<Tehing> getTehinguteAjalugu() {
        return tehingud;
    }
    // Tehingute ajaloo kuvamise funktsioon
    public void kuvaTehinguteAjalugu() {
        for (Tehing tehing : tehingud) {
            System.out.println("** Tehingu kirjeldus **");
            System.out.println("Saatja: " + Konto.getKasutajaNimi());
            System.out.println("Saaja: " + tehing.getSaaja());
            System.out.println("Selgitus: " + tehing.getSelgitus());
            System.out.println("Summa: " + tehing.getSumma());
            System.out.println("Kuupäev: " + tehing.getKuupäev());
            System.out.println();

        }
    }
    // Meetod tehingute faili kirjutamiseks (Kui tahab)
    public void kirjutaFaili(String failinimi){
        try {
            FileWriter fileWriter = new FileWriter(new File(failinimi), true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            Set<String> unikaalsedTehingud = new HashSet<>();

            //Tehingu info faili salvestamine
            for (Tehing tehing: tehingud) {
                String tehinguInfo = "Saatja: " + Konto.getKasutajaNimi() +
                        "\nSaaja: " + tehing.getSaaja() +
                        "\nSelgitus: " + tehing.getSelgitus() +
                        "\nSumma: " + tehing.getSumma() +
                        "\nKuupäev: " + formatter.format(tehing.getKuupäev());
                if (unikaalsedTehingud.add(tehinguInfo)) {
                    printWriter.println("** Tehingu Kirjeldus **");
                    printWriter.println(tehinguInfo);
                    printWriter.println();
                }
            }
            sorteeriTehingudKuupäevaJärgi(tehingud);
            printWriter.close();
            System.out.println("Tehingu info on salvestatud faili " + failinimi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Meetod tehingute failist lugemiseks (Kui tahta)
    public void loeFaili(String failinimi) {
        try {
            File file = new File(failinimi);
            BufferedReader reader = new BufferedReader(new FileReader(failinimi));
            String line;
            ArrayList<Tehing> tehingud = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                // Loetakse failist iga tehingu kirjeldus
                String kasutajaNimi = reader.readLine().substring(8); // "Saatja: " jäetakse välja
                String saaja = reader.readLine().substring(7); // "Saaja: " jäetakse välja
                String selgitus = reader.readLine().substring(10); // "Selgitus: " jäetakse välja
                double summa = Double.parseDouble(reader.readLine().substring(7)); // "Summa: " jäetakse välja
                LocalDateTime kuupäev = LocalDateTime.parse(reader.readLine().substring(9), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")); // "Kuupäev: " jäetakse välja
                reader.readLine(); // Tühirida

                // Lisatakse tehing tehingute listi
                tehingud.add(new Tehing(kasutajaNimi, saaja, selgitus, summa, kuupäev));
            }
            reader.close();
            tehingud.sort(Comparator.comparing(Tehing::getKuupäev));

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
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sorteeriTehingudKuupäevaJärgi(ArrayList<Tehing> tehingud) {
        LocalDateTime praeguneHetk = LocalDateTime.now();
        this.tehingud.sort(Comparator.comparingLong(t -> Duration.between(t.getKuupäev(), praeguneHetk).toMillis()));
    /*public void sorteeriTehingudKuupäevaJärgi() {
        Collections.sort(tehingud, new Comparator<Tehing>() {
            @Override
            public int compare(Tehing t1, Tehing t2) {
                return t2.getKuupäev().compareTo(t1.getKuupäev());
            }
        });
    */}

}
