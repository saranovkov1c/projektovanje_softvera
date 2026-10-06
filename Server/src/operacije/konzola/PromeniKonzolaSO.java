package operacije.konzola;

import domen.Konzola;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class PromeniKonzolaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Konzola)) {
            throw new Exception("Parametar mora biti Konzola");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Konzola k = (Konzola) param;
        broker.edit(k);
    }
}
