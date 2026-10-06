package operacije.klijent;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class PretraziKlijentSO extends ApstraktnaGenerickaOperacija {
    private Klijent klijent;

    public Klijent getKlijent() {
        return klijent;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Integer)) {
            throw new Exception("Parametar mora biti ID klijenta (Integer)");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        int id = (int) param;
        String uslov = "WHERE idKlijent = " + id;
        List<OpstiDomenskiObjekat> lista = broker.getAll(new Klijent(), uslov);
        if (lista.isEmpty()) {
            throw new Exception("Klijent sa ID " + id + " ne postoji");
        }
        klijent = (Klijent) lista.get(0);
    }
}
