package repository.db;

import repository.Repository;

/**
 * 
 * 
 * @author SARA
 */
public interface DbRepository<T> extends Repository<T> {

    default public void connect() throws Exception {
        DbConnection.getInstance().getConnection();
    }

    default public void disconnect() throws Exception {
        DbConnection.getInstance().getConnection().close();
    }

    default public void commit() throws Exception {
        DbConnection.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception {
        DbConnection.getInstance().getConnection().rollback();
    }
}
