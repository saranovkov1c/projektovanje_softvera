package operacije.clanskakartica;

import domen.ClanskaKartica;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class UbaciClanskaKarticaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof ClanskaKartica)) {
            throw new Exception("Parametar mora biti ClanskaKartica");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        ClanskaKartica ck = (ClanskaKartica) param;
        broker.add(ck);
    }
}
