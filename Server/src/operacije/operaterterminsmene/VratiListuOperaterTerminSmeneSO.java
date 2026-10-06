package operacije.operaterterminsmene;

import domen.OperaterTerminSmene;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuOperaterTerminSmeneSO extends ApstraktnaGenerickaOperacija {
    private List<OperaterTerminSmene> lista;

    public List<OperaterTerminSmene> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Integer)) {
            throw new Exception("Parametar mora biti ID operatera (Integer)");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        int idOperater = (int) param;
        String uslov = "WHERE idOperater = " + idOperater + " ORDER BY datum";
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new OperaterTerminSmene(), uslov);
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((OperaterTerminSmene) odo);
        }
    }
}
