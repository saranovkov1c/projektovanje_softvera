package operacije.klijent;

import domen.Klijent;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class ObrisiKlijentSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Klijent)) {
            throw new Exception("Parametar mora biti Klijent");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Klijent k = (Klijent) param;
        broker.delete(k);
    }
}
