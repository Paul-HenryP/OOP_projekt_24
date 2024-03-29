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
    private static String parool; // 2
    private static String eMail; // 3

    private static String kontoNumber;

    private static ArrayList<String>  kontaktid;

    private static double saldo;

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

    public static void setKasutajaNimi(String kasutajaNimi) {
        Konto.kasutajaNimi = kasutajaNimi;
    }

    public String getParool() {
        return parool;
    }

    public static void setParool(String parool) {
        Konto.parool = parool;
    }

    public String geteMail() {
        return eMail;
    }

    public static void seteMail(String eMail) {
        Konto.eMail = eMail;
    }

    public String getKontoNumber() {
        return kontoNumber;
    }
    public ArrayList<String> getKontaktid() {
        return kontaktid;
    }

    public static double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo += saldo;
    }

    @Override
    public String toString() {
        return "Konto( " +
                "kasutajaNimi: '" + kasutajaNimi + '\'' +
                ", parool: '" + parool + '\'' +
                ", eMail: '" + eMail + '\'' +
                ", kontoNumber: '" + kontoNumber + '\'' +
                ')';
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
        ArrayList<String> kontaktideList = new ArrayList<String>();

        String kontonrViimased8Numbrit = "";
        for (int i = 0; i < 8; i++) { //Genereerib viimased 8 numbrit konto numbrile.
            Random rand = new Random();
            int rand_int = rand.nextInt(14);

            kontonrViimased8Numbrit += String.valueOf(rand_int);
        }

        Konto isik =  new Konto(kasutajanimi, parool, " ", "EE1088"+kontonrViimased8Numbrit, kontaktideList, 0); // Loob konto.

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

