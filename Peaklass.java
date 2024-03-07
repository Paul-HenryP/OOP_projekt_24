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
        String sisestatakse = JOptionPane.showInputDialog(null, "Tere tulemast internetipanka, sisesta oma kasutajanimi ", "Kasutajanimi",
                JOptionPane.QUESTION_MESSAGE);

        System.out.println(sisestatakse);
    }
}