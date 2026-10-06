package repository.db.implementacija;

import java.sql.*;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbConnection;
import repository.db.DbRepository;

/**
 * 
 * 
 * @author SARA
 */
public class DbRepositoryGeneric implements DbRepository<OpstiDomenskiObjekat> {

    @Override
    public void add(OpstiDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele()
                + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES ("
                + param.vratiVrednostiZaUbacivanje() + ")";
        System.out.println(upit);
        Statement st = DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            int generisaniId = rs.getInt(1);
            param.vratiGenerisaniKljuc(generisaniId);
        }
        rs.close();
        st.close();
    }

    @Override
    public void edit(OpstiDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(OpstiDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<OpstiDomenskiObjekat> getAll(OpstiDomenskiObjekat param, String uslov) throws Exception {
        List<OpstiDomenskiObjekat> lista;
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += " " + uslov;
        }
        System.out.println(upit);
        Statement st = DbConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        st.close();
        rs.close();
        return lista;
    }

    @Override
    public OpstiDomenskiObjekat get(OpstiDomenskiObjekat parametar, String uslov) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
