package Rühmatöö;

import java.util.Date;

/***************************************
 *
 *
 * Kirjeldus: Klass TehingAndmed sisaldab andmeid tehinguid tegevate osapoolte vahel, nagu saatja, saaja, selgitus, summa, kuupäev.
 * Lisaks sisaldab klass vajalikke gettereid.
 *
 * Autor: Andreas Kelder
 *
 **************************************/

// Tehingu objekti klass
public class Tehing {
    private static String saatja;
    private static String saaja;
    private static String selgitus;
    private static double summa;
    private static Date kuupäev;

    public Tehing(String saatja, String saaja, String selgitus, double summa, Date kuupäev) {
        this.saatja = saatja;
        this.saaja = saaja;
        this.selgitus = selgitus;
        this.summa = summa;
        this.kuupäev = kuupäev;
    }

    public static String getSaatja() {
        return saatja;
    }

    public static String getSaaja() {
        return saaja;
    }

    public static String getSelgitus() {
        return selgitus;
    }

    public static double getSumma() {
        return summa;
    }

    public static Date getKuupäev() {
        return kuupäev;
    }
}
