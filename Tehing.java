
package Rühmatöö;

import java.time.LocalDateTime;

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
    private  String saatja;
    private  String saaja;
    private  String selgitus;
    private  double summa;
    private  LocalDateTime kuupäev;

    public Tehing(String saatja, String saaja, String selgitus, double summa, LocalDateTime kuupäev) {
        this.saatja = saatja;
        this.saaja = saaja;
        this.selgitus = selgitus;
        this.summa = summa;
        this.kuupäev = kuupäev;
    }

    public  String getSaatja() {
        return saatja;
    }

    public  String getSaaja() {
        return saaja;
    }

    public  String getSelgitus() {
        return selgitus;
    }

    public  double getSumma() {
        return summa;
    }

    public LocalDateTime getKuupäev() {
        return kuupäev;
    }
}
