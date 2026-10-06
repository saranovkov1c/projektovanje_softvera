package operacije.konzola;

import domen.Konzola;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuSviKonzolaSO extends ApstraktnaGenerickaOperacija {
    private List<Konzola> lista;

    public List<Konzola> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new Konzola(), null);
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((Konzola) odo);
        }
    }
}
