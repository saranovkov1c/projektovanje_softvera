package operacije.terminsmene;

import domen.OpstiDomenskiObjekat;
import domen.TerminSmene;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuSviTerminSmeneSO extends ApstraktnaGenerickaOperacija {
    private List<TerminSmene> lista;

    public List<TerminSmene> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new TerminSmene(), null);
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((TerminSmene) odo);
        }
    }
}
