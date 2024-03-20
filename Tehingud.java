package Rühmatöö;

import java.util.ArrayList;
import java.util.Date;
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
    public void lisaTehing(String saatja, String saaja, String selgitus, double summa, Date kuupäev) {
        Tehing tehing = new Tehing(saatja, saaja, selgitus, summa, kuupäev);
        tehingud.add(tehing);
    }
    // Meetod tehingute saamiseks
    public ArrayList<Tehing> getTehinguteAjalugu() {
        return tehingud;
    }
    // Meetod tehingute faili kirjutamiseks (Kui tahab)
    public void kirjutaFaili(String failinimi){
    //.....
    }
    // Meetod tehingute failist lugemiseks (Kui tahta)
    public void loeFaili(String failinimi) {
    //...
    }
    // Tehingute ajaloo kuvamise funktsioon
    public void kuvaTehinguteAjalugu() {
        for (Tehing tehing : tehingud) {
            System.out.println("** Tehingu kirjeldus **");
            System.out.println("Saatja: " + tehing.getSaatja());
            System.out.println("Saaja: " + tehing.getSaaja());
            System.out.println("Selgitus: " + tehing.getSelgitus());
            System.out.println("Summa: " + tehing.getSumma());
            System.out.println("Kuupäev: " + tehing.getKuupäev());
            System.out.println();

        }
    }
}
