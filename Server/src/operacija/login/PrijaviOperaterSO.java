package operacija.login;

import domen.Operater;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class PrijaviOperaterSO extends ApstraktnaGenerickaOperacija {

    private Operater operater;

    public Operater getOperater() {
        return operater;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Operater)) {
            throw new Exception("Parametar mora biti Operater");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Operater uneto = (Operater) param;
        List<Operater> operateri = broker.getAll(uneto, null);

        for (Operater o : operateri) {
            if (o.getKorisnickoIme().equals(uneto.getKorisnickoIme())
                    && o.getSifra().equals(uneto.getSifra())) {
                operater = o;
                return;
            }
        }
        operater = null;
    }
}
