/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SARA
 */
public class TerminSmene implements OpstiDomenskiObjekat, Serializable {

    private int idTerminSmene;
    private String napomena;
    private String tipTermina;

    public TerminSmene(int idTerminSmene, String napomena, String tipTermina) {
        this.idTerminSmene = idTerminSmene;
        this.napomena = napomena;
        this.tipTermina = tipTermina;
    }

    public TerminSmene() {
    }

    public TerminSmene(int idTerminSmene) {
        this.idTerminSmene = idTerminSmene;
    }

    public int getIdTerminSmene() {
        return idTerminSmene;
    }

    public void setIdTerminSmene(int idTerminSmene) {
        this.idTerminSmene = idTerminSmene;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getTipTermina() {
        return tipTermina;
    }

    public void setTipTermina(String tipTermina) {
        this.tipTermina = tipTermina;
    }

    @Override
    public String toString() {
        return tipTermina;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TerminSmene other = (TerminSmene) obj;
        return this.idTerminSmene == other.idTerminSmene;
    }

    @Override
    public String vratiNazivTabele() {
        return "terminsmene";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("terminsmene.idTerminSmene");
            String napomena2 = rs.getString("terminsmene.napomena");
            String tipTermina2 = rs.getString("terminsmene.tipTermina");

            TerminSmene ts = new TerminSmene(id, napomena2, tipTermina2);
            lista.add(ts);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "napomena,tipTermina";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + napomena + "','" + tipTermina + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "terminsmene.idTerminSmene=" + idTerminSmene;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int id = rs.getInt("terminsmene.idTerminSmene");
            String napomena2 = rs.getString("terminsmene.napomena");
            String tipTermina2 = rs.getString("terminsmene.tipTermina");

            TerminSmene ts = new TerminSmene(id, napomena2, tipTermina2);
            return ts;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "napomena='" + napomena + "', tipTermina='" + tipTermina + "'";
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idTerminSmene = id;
    }
}
