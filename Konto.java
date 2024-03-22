
package Rühmatöö;

import java.util.ArrayList;
import java.util.Random;

/***********************************
 *
 *
 * Kirjeldus: Klass sisaldab teavet konto omanikukohta nagu kasutajanimi, parool, kontaktide nimekiri kellele tehinguid teha
 * ja uue kontaki lisamist ja vastavaid getter/settereid. Random genereerimist saaks kasutada kontaktide loomisel vms.
 *
 * Autor: Paul-Henry Paltmann
 *
 **********************************/


public class Konto {
    public static String kasutajaNimi; // 1
    private String parool; // 2
    private String eMail; // 3

    private String kontoNumber;

    private ArrayList<String>  kontaktid;

    private double saldo;

    public static ArrayList<Konto>  kõikLoodudKontod = new ArrayList<>();
//    static ArrayList<Konto> kõikLoodudKontodeList = new ArrayList<>();


    public Konto(String kasutajaNimi, String parool, String eMail, String kontoNumber, ArrayList<String> kontaktid, double saldo) {
        this.kasutajaNimi = kasutajaNimi;
        this.parool = parool;
        this.eMail = eMail;
        this.kontoNumber = kontoNumber;
        this.kontaktid = kontaktid;
        this.saldo = saldo;
    }

    public static String getKasutajaNimi() {
        return kasutajaNimi;
    }

    public void setKasutajaNimi(String kasutajaNimi) {
        this.kasutajaNimi = kasutajaNimi;
    }

    public String getParool() {
        return parool;
    }

    public void setParool(String parool) {
        this.parool = parool;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getKontoNumber() {
        return kontoNumber;
    }
    public ArrayList<String> getKontaktid() {
        return kontaktid;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo += saldo;
    }

    @Override
    public String toString() {
        return "Konto{" +
                "kasutajaNimi='" + kasutajaNimi + '\'' +
                ", parool='" + parool + '\'' +
                ", eMail='" + eMail + '\'' +
                ", kontoNumber='" + kontoNumber + '\'' +
                '}';
    }

    public void muudaAndmeid(int x, String uus){ // X asemel siis mida soovitakse uuendada kas 1, 2 või 3 ja uued andmed teise parameetriga.

        if (x == 1){
            setKasutajaNimi(uus);
        } else if (x == 2) {
            setParool(uus);
        } else {
            seteMail(uus);
        }
    }

    public static void looKOnto(String kasutajanimi, String parool ){
        Random rand = new Random();
        int rand_int = rand.nextInt(14);
        ArrayList<String> kontaktideList = new ArrayList<String>();
        Konto isik =  new Konto(kasutajanimi, parool, " ", "EE1088"+String.valueOf(rand_int), kontaktideList, 0); // Loob konto.

        kõikLoodudKontod.add(isik);

    }

    public boolean teeMakse(String saajaKasutajanimi, String selgitus, double summa) {

        if (kõikLoodudKontod ==  null){
            return false;
        }

        for (Konto konto : kõikLoodudKontod) { //tagastab true kui leiab konto ja lisab raha, false kui ei leia.
            if (Konto.this.getKasutajaNimi().equals(saajaKasutajanimi)) { //Kui kasutajanimi kattub sisendiga ss lisatakse summa saajale ja lahtutatakse saatja kontolt
                this.setSaldo(summa);

                return true; //Peaklassis tuleb ss saatjalt summa lahutada kui tehakse tehing true tagastusega.
            } else return false;

        }
        return false;
    }

    public static boolean kontoOlemasoluKontroll(ArrayList<Konto> a, String kasutajanimi){
        if (a ==  null){
            return false;
        }

        for (Konto el : a) {
            if (el.kasutajaNimi.equals(kasutajanimi)){
                return true;
            }
        }
        return false;
    }

    public static Konto leiakonto(String kasutajanimi) {
        if (kõikLoodudKontod ==  null){
            return null;
        }

        for (Konto el : kõikLoodudKontod) {
            if (el.kasutajaNimi.equals(kasutajanimi)){
                return el;
            }
        }
        return null;
    }


}


