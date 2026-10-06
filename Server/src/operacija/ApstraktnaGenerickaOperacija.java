package operacija;

import repository.Repository;
import repository.db.DbConnection;
import repository.db.DbRepository;
import repository.db.implementacija.DbRepositoryGeneric;

/**
 * 
 * 
 * @author SARA
 */
public abstract class ApstraktnaGenerickaOperacija {
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            zapocniTransakciju();
            preduslovi(objekat);
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
            ugasiKonekciju();
        }
    }

    protected abstract void preduslovi(Object param) throws Exception;

    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;

    private void zapocniTransakciju() throws Exception {
        ((DbRepository) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DbRepository) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        if (DbConnection.getInstance().getConnection() != null && !DbConnection.getInstance().getConnection().isClosed()) {
            ((DbRepository) broker).rollback();
        }
    }

    private void ugasiKonekciju() throws Exception {
        ((DbRepository) broker).disconnect();
    }
}
