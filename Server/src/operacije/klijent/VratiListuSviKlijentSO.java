package operacije.klijent;

import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuSviKlijentSO extends ApstraktnaGenerickaOperacija {
    private List<Klijent> lista;

    public List<Klijent> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new Klijent(), null);
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((Klijent) odo);
        }
    }
}
