package operacije.operater;

import domen.OpstiDomenskiObjekat;
import domen.Operater;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuSviOperaterSO extends ApstraktnaGenerickaOperacija {
    private List<Operater> lista;

    public List<Operater> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new Operater(), null);
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((Operater) odo);
        }
    }
}
