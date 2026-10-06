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
public class Konzola implements OpstiDomenskiObjekat, Serializable{
    private int idKonzola;
    private String naziv;
    private String proizvodjac;
    private double cenaPoSatu;

    public Konzola(int idKonzola, String naziv, String proizvodjac, double cenaPoSatu) {
        this.idKonzola = idKonzola;
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.cenaPoSatu = cenaPoSatu;
    }

    public Konzola() {
    }

    public Konzola(int idKonzola) {
        this.idKonzola = idKonzola;
    }

    public int getIdKonzola() {
        return idKonzola;
    }

    public void setIdKonzola(int idKonzola) {
        this.idKonzola = idKonzola;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Konzola other = (Konzola) obj;
        return this.idKonzola == other.idKonzola;
    }

    @Override
    public String vratiNazivTabele() {
        return "konzola";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("konzola.idKonzola");
            String naziv2 = rs.getString("konzola.naziv");
            String proizvodjac2 = rs.getString("konzola.proizvodjac");
            double cenaPoSatu2 = rs.getDouble("konzola.cenaPoSatu");

            Konzola k = new Konzola(id, naziv2, proizvodjac2, cenaPoSatu2);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,proizvodjac,cenaPoSatu";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "','" + proizvodjac + "'," + cenaPoSatu;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "konzola.idKonzola=" + idKonzola;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int id = rs.getInt("konzola.idKonzola");
            String naziv2 = rs.getString("konzola.naziv");
            String proizvodjac2 = rs.getString("konzola.proizvodjac");
            double cenaPoSatu2 = rs.getDouble("konzola.cenaPoSatu");

            Konzola k = new Konzola(id, naziv2, proizvodjac2, cenaPoSatu2);
            return k;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', proizvodjac='" + proizvodjac + "', cenaPoSatu=" + cenaPoSatu;
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idKonzola = id;
    }
}
