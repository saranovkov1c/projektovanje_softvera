package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.Konzola;
import domen.OpstiDomenskiObjekat;
import domen.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class PretraziIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {
    private Iznajmljivanje iznajmljivanje;

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Integer)) {
            throw new Exception("Parametar mora biti ID iznajmljivanja (Integer)");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        int idIznajmljivanje = (int) param;

        String uslovIznajmljivanje = "WHERE idIznajmljivanje = " + idIznajmljivanje;
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new Iznajmljivanje(), uslovIznajmljivanje);
        if (objekti.isEmpty()) {
            throw new Exception("Iznajmljivanje ne postoji");
        }
        iznajmljivanje = (Iznajmljivanje) objekti.get(0);

        String uslovStavke = "WHERE idIznajmljivanje = " + idIznajmljivanje + " ORDER BY rb";
        List<OpstiDomenskiObjekat> stavkeObj = broker.getAll(new StavkaIznajmljivanja(), uslovStavke);
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : stavkeObj) {
            stavke.add((StavkaIznajmljivanja) odo);
        }

        for (StavkaIznajmljivanja si : stavke) {
            int idKonzola = si.getKonzola().getIdKonzola();
            String uslovKonzola = "WHERE idKonzola = " + idKonzola;
            List<OpstiDomenskiObjekat> konzoleObj = broker.getAll(new Konzola(), uslovKonzola);
            if (!konzoleObj.isEmpty()) {
                si.setKonzola((Konzola) konzoleObj.get(0));
            }
        }

        iznajmljivanje.setStavke(stavke);
    }
}
