package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.IznajmljivanjePretraga;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {
    private List<Iznajmljivanje> lista;

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof IznajmljivanjePretraga)) {
            throw new Exception("Parametar mora biti IznajmljivanjePretraga");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        IznajmljivanjePretraga ip = (IznajmljivanjePretraga) param;
        StringBuilder uslov = new StringBuilder("WHERE 1=1");

        if (ip.getIdOperater() != null) {
            uslov.append(" AND idOperater = ").append(ip.getIdOperater());
        }
        if (ip.getIdKlijent() != null) {
            uslov.append(" AND idKlijent = ").append(ip.getIdKlijent());
        }
        if (ip.getDatumOd() != null) {
            uslov.append(" AND datumIzdavanja >= '").append(ip.getDatumOd()).append("'");
        }
        if (ip.getDatumDo() != null) {
            uslov.append(" AND datumIzdavanja <= '").append(ip.getDatumDo()).append("'");
        }
        if (ip.getIznosOd() != null) {
            uslov.append(" AND ukupanIznos >= ").append(ip.getIznosOd());
        }
        if (ip.getIznosDo() != null) {
            uslov.append(" AND ukupanIznos <= ").append(ip.getIznosDo());
        }
        if (ip.getIdKonzola() != null) {
            uslov.append(" AND EXISTS (SELECT 1 FROM stavkaiznajmljivanja si WHERE si.idIznajmljivanje = iznajmljivanje.idIznajmljivanje AND si.idKonzola = ")
                 .append(ip.getIdKonzola()).append(")");
        }

        List<OpstiDomenskiObjekat> objekti = broker.getAll(new Iznajmljivanje(), uslov.toString());
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((Iznajmljivanje) odo);
        }
    }
}
