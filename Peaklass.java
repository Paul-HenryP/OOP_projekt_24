/***********************************
 *
 *
 * Kirjeldus: Klass sisaldab suhtlust kasutajaga ja meetodite väljakutseid.
 *
 * Autor: -
 *
 **********************************/

import javax.swing.JOptionPane;


public class Peaklass{



    public static void main(String[] args) {
        String sisestatakseKasutajanimi = JOptionPane.showInputDialog(null, "Tere tulemast internetipanka, sisesta oma kasutajanimi ", "Kasutajanimi",
                JOptionPane.QUESTION_MESSAGE);
        System.out.println(sisestatakseKasutajanimi);

        String sisestatakseParool = JOptionPane.showInputDialog(null, " Sisesta oma parool ", "Parool",
                JOptionPane.QUESTION_MESSAGE);

        System.out.println(sisestatakseParool);

        //Kontroll kas konto isend on olemas, kui pole siis luuakse. Randomiga genereeritakse konto number kindlas pikkuses.


        //Valige soovitud tegevus: Makse, andmete muutmine, konto saldo, logi välja.
    }
}