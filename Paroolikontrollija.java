package Rühmatöö;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Paroolikontrollija {
    public static boolean kontrolliSalvestatudParooli(String kasutajanimi, String sisestatudParool) {
        String failinimi = "kasutajad.txt"; // Failinimi, kus kasutajanime ja parooli paarid on salvestatud
        File fail = new File(failinimi);

        if (!fail.exists()) {
            looFail(kasutajanimi, sisestatudParool, failinimi); // Kui faili pole, siis loome selle ja salvestame kasutajanime ja parooli
        }

        try {
            // Loeme failist rida-realt kasutajanime ja paroole ning kontrollime, kas sisestatud parool klapib mõne kasutajanimega
            for (String line : Files.readAllLines(Paths.get(failinimi))) {
                String[] kasutajaJaParool = line.split(":");
                if (kasutajaJaParool.length == 2) {
                    String kasutajaFailist = kasutajaJaParool[0];
                    String paroolFailist = kasutajaJaParool[1];

                    if (kasutajaFailist.equals(kasutajanimi) && paroolFailist.equals(sisestatudParool)) {
                        return true; // Parool õige, tagastame true
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Viga faili lugemisel: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return false; // Parool ei sobinud ühegi kasutajaga
    }

    static void looFail(String kasutajanimi, String parool, String failinimi) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(failinimi, true))) {
            // Lisame faili uue kasutajanime ja parooli, kasutades append režiimi (true)
            writer.write(kasutajanimi + ":" + parool);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Viga faili kirjutamisel: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
