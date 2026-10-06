package operacije.terminsmene;

import domen.TerminSmene;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class PromeniTerminSmeneSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof TerminSmene)) {
            throw new Exception("Parametar mora biti TerminSmene");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        TerminSmene ts = (TerminSmene) param;
        broker.edit(ts);
    }
}
