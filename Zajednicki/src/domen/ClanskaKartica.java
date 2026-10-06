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
public class ClanskaKartica implements OpstiDomenskiObjekat, Serializable{
    private int idClanskaKartica;
    private String kategorija;

    public ClanskaKartica(int idClanskaKartica, String kategorija) {
        this.idClanskaKartica = idClanskaKartica;
        this.kategorija = kategorija;
    }

    public ClanskaKartica() {
    }

    public ClanskaKartica(int idClanskaKartica) {
        this.idClanskaKartica = idClanskaKartica;
    }

    public int getIdClanskaKartica() {
        return idClanskaKartica;
    }

    public void setIdClanskaKartica(int idClanskaKartica) {
        this.idClanskaKartica = idClanskaKartica;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return kategorija;
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
        final ClanskaKartica other = (ClanskaKartica) obj;
        return this.idClanskaKartica == other.idClanskaKartica;
    }

    @Override
    public String vratiNazivTabele() {
        return "clanskakartica";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("clanskakartica.idClanskaKartica");
            String kategorija2 = rs.getString("clanskakartica.kategorija");

            ClanskaKartica ck = new ClanskaKartica(id, kategorija2);
            lista.add(ck);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "kategorija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + kategorija + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "clanskakartica.idClanskaKartica=" + idClanskaKartica;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int id = rs.getInt("clanskakartica.idClanskaKartica");
            String kategorija2 = rs.getString("clanskakartica.kategorija");

            ClanskaKartica ck = new ClanskaKartica(id, kategorija2);
            return ck;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "kategorija='" + kategorija + "'";
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idClanskaKartica = id;
    }
}
