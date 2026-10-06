package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.OpstiDomenskiObjekat;
import domen.StavkaIznajmljivanja;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class PromeniIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Iznajmljivanje)) {
            throw new Exception("Parametar mora biti Iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Iznajmljivanje iznajmljivanje = (Iznajmljivanje) param;

        broker.edit(iznajmljivanje);

        String uslov = "WHERE idIznajmljivanje = " + iznajmljivanje.getIdIznajmljivanje();
        List<OpstiDomenskiObjekat> stareStavke = broker.getAll(new StavkaIznajmljivanja(), uslov);
        for (OpstiDomenskiObjekat odo : stareStavke) {
            broker.delete(odo);
        }

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
