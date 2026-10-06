package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class UbaciIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Iznajmljivanje)) {
            throw new Exception("Parametar mora biti Iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) param;
        broker.add(iznajmljivanje);

        int rb = 1;
        if (iznajmljivanje.getStavke() != null) {
            for (StavkaIznajmljivanja si : iznajmljivanje.getStavke()) {
                si.setIznajmljivanje(iznajmljivanje);
                si.setRb(rb++);
                broker.add(si);
            }
        }
    }
}
