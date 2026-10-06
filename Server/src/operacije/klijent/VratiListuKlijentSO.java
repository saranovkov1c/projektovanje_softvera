package operacije.klijent;

import domen.Klijent;
import domen.KlijentPretraga;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuKlijentSO extends ApstraktnaGenerickaOperacija {

    private List<Klijent> lista;

    public List<Klijent> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof KlijentPretraga)) {
            throw new Exception("Parametar mora biti KlijentPretraga");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        KlijentPretraga kp = (KlijentPretraga) param;
        StringBuilder uslov = new StringBuilder();

        if (kp.getKategorijaKartice() != null && !kp.getKategorijaKartice().trim().isEmpty()) {
            uslov.append("JOIN clanskakartica ON klijent.idClanskaKartica = clanskakartica.idClanskaKartica ");
        }

        uslov.append("WHERE 1=1");

        if (kp.getIme() != null && !kp.getIme().trim().isEmpty()) {
            uslov.append(" AND ime LIKE '%").append(kp.getIme()).append("%'");
        }
        if (kp.getPrezime() != null && !kp.getPrezime().trim().isEmpty()) {
            uslov.append(" AND prezime LIKE '%").append(kp.getPrezime()).append("%'");
        }
        if (kp.getEmail() != null && !kp.getEmail().trim().isEmpty()) {
            uslov.append(" AND email LIKE '%").append(kp.getEmail()).append("%'");
        }
        if (kp.getTelefon() != null && !kp.getTelefon().trim().isEmpty()) {
            uslov.append(" AND telefon LIKE '%").append(kp.getTelefon()).append("%'");
        }
        if (kp.getIdClanskaKartica() != null) {
            uslov.append(" AND klijent.idClanskaKartica = ").append(kp.getIdClanskaKartica());
        }
        if (kp.getKategorijaKartice() != null && !kp.getKategorijaKartice().trim().isEmpty()) {
            uslov.append(" AND clanskakartica.kategorija = '").append(kp.getKategorijaKartice()).append("'");
        }

        List<OpstiDomenskiObjekat> objekti = broker.getAll(new Klijent(), uslov.toString());
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((Klijent) odo);
        }
    }
}
