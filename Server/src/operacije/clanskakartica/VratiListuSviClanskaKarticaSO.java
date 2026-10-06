package operacije.clanskakartica;

import domen.ClanskaKartica;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuSviClanskaKarticaSO extends ApstraktnaGenerickaOperacija {
    private List<ClanskaKartica> lista;

    public List<ClanskaKartica> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new ClanskaKartica(), null);
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((ClanskaKartica) odo);
        }
    }
}
