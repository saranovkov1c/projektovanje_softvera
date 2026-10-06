package operacije.operaterterminsmene;

import domen.OperaterTerminSmene;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class PromeniOperaterTerminSmeneSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof OperaterTerminSmene)) {
            throw new Exception("Parametar mora biti OperaterTerminSmene");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        OperaterTerminSmene ots = (OperaterTerminSmene) param;
        broker.edit(ots);
    }
}
