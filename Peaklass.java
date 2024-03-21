package Rühmatöö; /***********************************
 *
 *
 * Kirjeldus: Klass sisaldab suhtlust kasutajaga ja meetodite väljakutseid.
 *
 * Autor: Paul-Henry Paltmann,
 *
 **********************************/

import javax.swing.JOptionPane;
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

        if (Konto.kontoOlemasoluKontroll(kõikLoodudKontod, sisestatakseKasutajanimi) ){
            System.out.println("Konto leitud! Olete sisse logitud.");
        } else if (!Konto.kontoOlemasoluKontroll(kõikLoodudKontod, sisestatakseKasutajanimi)){
            System.out.println("Kontot ei leitud. Kas soovite uue konto registreerida? ");
            String kontoLuua = JOptionPane.showInputDialog(null, " Kas soovite uue konto registreerida? Jah/Ei ", "Kontot ei leitud.",
                    JOptionPane.QUESTION_MESSAGE);
            System.out.println(kontoLuua);

            if (kontoLuua.equalsIgnoreCase("jah")){
                Konto.looKOnto(sisestatakseKasutajanimi, sisestatakseParool);
                System.out.println("Teie andmed on järgnevad: ");
                System.out.println(leiakonto(sisestatakseKasutajanimi));
            }
        }



        //Kontroll kas konto isend on olemas, kui pole siis luuakse. Randomiga genereeritakse konto number kindlas pikkuses.


        //Valige soovitud tegevus: Makse, andmete muutmine, konto saldo, logi välja.


        //Tehingute ajaloo vaatamise võimalus
        if (Konto.kontoOlemasoluKontroll(Konto.kõikLoodudKontod,kasutajaNimi )) { // Kui konto olemas, siis näed tehinguid.
            Tehingud tehingud = new Tehingud();
            tehingud.loeFaili("tehingud.txt");
            tehingud.kuvaTehinguteAjalugu();
        }
    }

}
