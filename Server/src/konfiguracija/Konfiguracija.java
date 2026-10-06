package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 
 * @author SARA
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties konfiguracija;

    private Konfiguracija() throws FileNotFoundException {
        konfiguracija = new Properties();
        try {
            konfiguracija.load(new FileInputStream("config/config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.getLogger(Konfiguracija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            try {
                instance = new Konfiguracija();
            } catch (FileNotFoundException ex) {
                System.getLogger(Konfiguracija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene() throws IOException {
        try {
            konfiguracija.store(new FileOutputStream("config/config.properties"), null);
        } catch (FileNotFoundException ex) {
            System.getLogger(Konfiguracija.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
