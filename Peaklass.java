package Rühmatöö; /***********************************
 *
 *
 * Kirjeldus: Klass sisaldab suhtlust kasutajaga ja meetodite väljakutseid.
 *
 * Autor: Paul-Henry Paltmann,
 *
 **********************************/

import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static Rühmatöö.Konto.*;


public class Peaklass{

    ArrayList<Konto> loodudKontod = new ArrayList<>();
    static Tehingud tehingud = new Tehingud();



    public static void main(String[] args) {


        String sisestatakseKasutajanimi = JOptionPane.showInputDialog(null, "Tere tulemast internetipanka, sisesta oma kasutajanimi ", "Kasutajanimi",
                JOptionPane.QUESTION_MESSAGE);
        System.out.println(sisestatakseKasutajanimi);

        String sisestatakseParool = JOptionPane.showInputDialog(null, " Sisesta oma parool ", "Parool",
                JOptionPane.QUESTION_MESSAGE);

        System.out.println(sisestatakseParool);

        if (Konto.kontoOlemasoluKontroll(kõikLoodudKontod, sisestatakseKasutajanimi)) { //Kas konto on olemas.
            System.out.println("Konto leitud! Olete sisse logitud.");
            laeVarasemadTehingud(sisestatakseKasutajanimi);
        } else if (!Konto.kontoOlemasoluKontroll(kõikLoodudKontod, sisestatakseKasutajanimi)) { //Kui pole.
            System.out.println("Kontot ei leitud. Kas soovite uue konto registreerida? ");
            String kontoLuua = JOptionPane.showInputDialog(null, " Kas soovite uue konto registreerida? Jah/Ei ", "Kontot ei leitud.",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(kontoLuua);

            if (kontoLuua.equalsIgnoreCase("jah")) {
                Konto.looKOnto(sisestatakseKasutajanimi, sisestatakseParool);
                System.out.println("Teie andmed on järgnevad: ");
                System.out.println(leiakonto(sisestatakseKasutajanimi));
            } else {
                System.out.println("Nägemist! ");
            }

        }


        //Kontroll kas konto isend on olemas, kui pole siis luuakse. Randomiga genereeritakse konto number kindlas pikkuses.


        //Valige soovitud tegevus: Makse, andmete muutmine, konto saldo, logi välja.

        while (true) {
            String sisestaTegevus = JOptionPane.showInputDialog(null, "Valige soovitud teveus: Makse, Andmete muutmine, Saldo, Lae varasemad tehingud failist (Pole veel saadaval), Logi välja ", "Tegevus",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(sisestaTegevus);
            if (sisestaTegevus.equalsIgnoreCase("Logi välja")) {
                System.out.println("Nägemiseni!");
                break;

            } else if (sisestaTegevus.equalsIgnoreCase("makse")) {
                System.out.println("Makse teostamine...");
                String sisestaSaaja = JOptionPane.showInputDialog(null, "Valige saaja ", "Saaja",
                        JOptionPane.QUESTION_MESSAGE);
                String sisestaSelgitus = JOptionPane.showInputDialog(null, "Valige selgitus ", "Selgitus",
                        JOptionPane.QUESTION_MESSAGE);
                double sisestaSumma = Double.parseDouble(JOptionPane.showInputDialog(null, "Valige summa ", "Summa",
                        JOptionPane.QUESTION_MESSAGE));
                if (Konto.getSaldo() - sisestaSumma >= 0) {
                    Tehing makse = new Tehing(Konto.getKasutajaNimi(), sisestaSaaja, sisestaSelgitus, sisestaSumma, LocalDateTime.now());
                    System.out.println("Makse teostatud."); //Peaks eelnevalt kontrollima kas on piisavalt raha.
                    System.out.println(makse);
                    Tehingud.lisaTehing(Konto.getKasutajaNimi(), sisestaSaaja, sisestaSelgitus, sisestaSumma, LocalDateTime.now());
                    Tehingud.kirjutaFaili("tehingud_ajalugu.txt");
                    System.out.println("Makse salvestatud faili.");
                } else if (!(Konto.getSaldo() - sisestaSumma >= 0)) {
                    System.out.println("Kontol on liiga vähe raha! ");
                }
            } else if (sisestaTegevus.equalsIgnoreCase("saldo")) {
                JOptionPane.showMessageDialog(null, "Teie saldo: " + Konto.getSaldo(), "Saldo",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println(Konto.getSaldo());
            } else if ((sisestaTegevus.equalsIgnoreCase("andmete muutmine"))) {
                String sisestaMuudetav = JOptionPane.showInputDialog(null, "Mida soovite muuta? Valikud: parool, e-mail, kasutajanimi.", "Muuda andmeid",
                        JOptionPane.QUESTION_MESSAGE);
                if (sisestaMuudetav.equalsIgnoreCase("parool")) {
                    String sisestaUus = JOptionPane.showInputDialog(null, "Sisestage uus väärtus.", "Muuda parooli",
                            JOptionPane.QUESTION_MESSAGE);
                    Konto.setParool(sisestaUus);
                } else if (sisestaMuudetav.equalsIgnoreCase("e-mail")) {
                    String sisestaUus = JOptionPane.showInputDialog(null, "Sisestage uus aadress.", "Muuda e-maili aadressi",
                            JOptionPane.QUESTION_MESSAGE);
                    Konto.seteMail(sisestaUus);

                } else if (sisestaMuudetav.equalsIgnoreCase("kasutajanimi")) {
                    String sisestaUus = JOptionPane.showInputDialog(null, "Sisestage uus kasutajanimi.", "Muuda kasutajanime",
                            JOptionPane.QUESTION_MESSAGE);
                    Konto.setKasutajaNimi(sisestaUus);

                }
                System.out.println("Teie andmed on järgnevad: ");
                System.out.println(leiakonto(sisestatakseKasutajanimi));

            } else if ((sisestaTegevus.equalsIgnoreCase("lae varasemad tehingud failist"))) {
                laeVarasemadTehingud(sisestatakseKasutajanimi);
            }
        }
    }
private static void laeVarasemadTehingud(String kasutajanimi) {
    String failinimi = "tehingud_ajalugu.txt";
    File fail = new File(failinimi);

    if (fail.exists()) { // Kontrollime, kas fail eksisteerib
        try (BufferedReader reader = new BufferedReader(new FileReader(failinimi))) {
            String line;
            ArrayList<Tehing> kasutajaTehingud = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.equals("** Tehingu kirjeldus **")) {
                    // Loeme failist iga tehingu kirjelduse
                    String saatja = reader.readLine().substring(8); // "Saatja: " jäetakse välja
                    String saaja = reader.readLine().substring(7); // "Saaja: " jäetakse välja
                    String selgitus = reader.readLine().substring(10); // "Selgitus: " jäetakse välja
                    double summa = Double.parseDouble(reader.readLine().substring(7)); // "Summa: " jäetakse välja
                    LocalDateTime kuupäev = LocalDateTime.parse(reader.readLine().substring(9), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")); // "Kuupäev: " jäetakse välja

                    // Kontrollime, kas tehingu saatja või saaja on sisestatud kasutajanimi
                    if (saatja.equalsIgnoreCase(kasutajanimi) || saaja.equalsIgnoreCase(kasutajanimi)) {
                        kasutajaTehingud.add(new Tehing(saatja, saaja, selgitus, summa, kuupäev));
                    }
                }
            }

            if (!kasutajaTehingud.isEmpty()) {
                StringBuilder tehinguteTekst = new StringBuilder();
                for (Tehing tehing : kasutajaTehingud) {
                    tehinguteTekst.append("** Tehingu kirjeldus **\n");
                    tehinguteTekst.append("Saatja: ").append(tehing.getSaatja()).append("\n");
                    tehinguteTekst.append("Saaja: ").append(tehing.getSaaja()).append("\n");
                    tehinguteTekst.append("Selgitus: ").append(tehing.getSelgitus()).append("\n");
                    tehinguteTekst.append("Summa: ").append(tehing.getSumma()).append("\n");
                    tehinguteTekst.append("Kuupäev: ").append(tehing.getKuupäev()).append("\n\n");
                }
                JOptionPane.showMessageDialog(null, "Teie tehingud: \n" + tehinguteTekst.toString(), "Tehingud", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Teil pole varasemaid tehinguid", "Tehingud", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            System.out.println("Viga faili lugemisel: " + e.getMessage());
            throw new RuntimeException(e);
        }
    } else {
        System.out.println("Faili " + failinimi + " ei leitud.");
        JOptionPane.showMessageDialog(null, "Teie saldo: " + Konto.getSaldo(), "Saldo", JOptionPane.INFORMATION_MESSAGE);
    }
}}
