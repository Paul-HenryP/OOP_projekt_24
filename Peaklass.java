package Rühmatöö; /***********************************
 *
 *
 * Kirjeldus: Klass sisaldab suhtlust kasutajaga ja meetodite väljakutseid.
 *
 * Autor: Paul-Henry Paltmann,
 *
 **********************************/

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static Rühmatöö.Konto.*;


public class Peaklass{

    ArrayList<Konto> loodudKontod = new ArrayList<>();



    public static void main(String[] args) {


        String sisestatakseKasutajanimi = JOptionPane.showInputDialog(null, "Tere tulemast internetipanka, sisesta oma kasutajanimi ", "Kasutajanimi",
                JOptionPane.QUESTION_MESSAGE);
        System.out.println(sisestatakseKasutajanimi);

        String sisestatakseParool = JOptionPane.showInputDialog(null, " Sisesta oma parool ", "Parool",
                JOptionPane.QUESTION_MESSAGE);

        System.out.println(sisestatakseParool);

        if (Konto.kontoOlemasoluKontroll(kõikLoodudKontod, sisestatakseKasutajanimi) ){ //Kas konto on olemas.
            System.out.println("Konto leitud! Olete sisse logitud.");
        } else if (!Konto.kontoOlemasoluKontroll(kõikLoodudKontod, sisestatakseKasutajanimi)){ //Kui pole.
            System.out.println("Kontot ei leitud. Kas soovite uue konto registreerida? ");
            String kontoLuua = JOptionPane.showInputDialog(null, " Kas soovite uue konto registreerida? Jah/Ei ", "Kontot ei leitud.",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(kontoLuua);

            if (kontoLuua.equalsIgnoreCase("jah")){
                Konto.looKOnto(sisestatakseKasutajanimi, sisestatakseParool);
                System.out.println("Teie andmed on järgnevad: ");
                System.out.println(leiakonto(sisestatakseKasutajanimi));
            } else {
                System.out.println("Nägemist! ");
            }

        }



        //Kontroll kas konto isend on olemas, kui pole siis luuakse. Randomiga genereeritakse konto number kindlas pikkuses.


        //Valige soovitud tegevus: Makse, andmete muutmine, konto saldo, logi välja.

        while (true){
            String sisestaTegevus = JOptionPane.showInputDialog(null, "Valige soovitud teveus: Makse, Andmete muutmine, Saldo, Lae varasemad tehingud failist (Pole veel saadaval), Logi välja ", "Tegevus",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(sisestaTegevus);
            if (sisestaTegevus.equalsIgnoreCase("Logi välja")){
                System.out.println("Nägemiseni!");
                break;

            } else if (sisestaTegevus.equalsIgnoreCase("makse")){
                System.out.println("Makse teostamine...");
                String sisestaSaaja = JOptionPane.showInputDialog(null, "Valige saaja ", "Saaja",
                        JOptionPane.QUESTION_MESSAGE);
                String sisestaSelgitus = JOptionPane.showInputDialog(null, "Valige selgitus ", "Selgitus",
                        JOptionPane.QUESTION_MESSAGE);
                double sisestaSumma = Double.parseDouble(JOptionPane.showInputDialog(null, "Valige summa ", "Summa",
                        JOptionPane.QUESTION_MESSAGE));
                if (Konto.getSaldo() - sisestaSumma >= 0){
                    Tehing makse = new Tehing(Konto.getKasutajaNimi(), sisestaSaaja,sisestaSelgitus, sisestaSumma, LocalDateTime.now() );
                    System.out.println("Makse teostatud."); //Peaks eelnevalt kontrollima kas on piisavalt raha.
                    System.out.println(makse);
                } else if (!(Konto.getSaldo() - sisestaSumma >= 0)) {
                    System.out.println("Kontol on liiga vähe raha! ");
                }
            } else if (sisestaTegevus.equalsIgnoreCase("saldo")) {
                JOptionPane.showMessageDialog(null, "Teie saldo: "+Konto.getSaldo(), "Saldo",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println(Konto.getSaldo());
            } else if ((sisestaTegevus.equalsIgnoreCase("andmete muutmine"))) {
                String sisestaMuudetav = JOptionPane.showInputDialog(null, "Mida soovite muuta? Valikud: parool, e-mail, kasutajanimi.", "Muuda andmeid",
                        JOptionPane.QUESTION_MESSAGE);
                if (sisestaMuudetav.equalsIgnoreCase("parool")){
                    String sisestaUus = JOptionPane.showInputDialog(null, "Sisestage uus väärtus.", "Muuda parooli",
                            JOptionPane.QUESTION_MESSAGE);
                    Konto.setParool(sisestaUus);
                } else if (sisestaMuudetav.equalsIgnoreCase("e-mail")){
                    String sisestaUus = JOptionPane.showInputDialog(null, "Sisestage uus aadress.", "Muuda e-maili aadressi",
                            JOptionPane.QUESTION_MESSAGE);
                    Konto.seteMail(sisestaUus);

                } else if (sisestaMuudetav.equalsIgnoreCase("kasutajanimi")){
                    String sisestaUus = JOptionPane.showInputDialog(null, "Sisestage uus kasutajanimi.", "Muuda kasutajanime",
                            JOptionPane.QUESTION_MESSAGE);
                    Konto.setKasutajaNimi(sisestaUus);

                }
                System.out.println("Teie andmed on järgnevad: ");
                System.out.println(leiakonto(sisestatakseKasutajanimi));

            }//Siia else-if'iga lisada juurde faili laadimise tegevus nagu eelnevad!

            //Tehingute ajaloo vaatamise võimalus
            //Kui kasutaja soovib, siis loetakse tehingute ajalugu failist. //Peaks tegema iga sisse logimise algul vb.
            if (Konto.kontoOlemasoluKontroll(kõikLoodudKontod, sisestatakseKasutajanimi) && sisestaTegevus.equalsIgnoreCase("lae tehingud failist")) { // Kui konto olemas, siis näed tehinguid. // if (sisu) copytud ülevalt.
                Tehingud tehingud = new Tehingud();
                tehingud.loeFaili("tehingud.txt");
                tehingud.kuvaTehinguteAjalugu();
            }




        }
    }
}
