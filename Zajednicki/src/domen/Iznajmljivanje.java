/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SARA
 */
public class Iznajmljivanje implements OpstiDomenskiObjekat, Serializable{
    private int idIznajmljivanje;
    private LocalDate datumIzdavanja;
    private double fiksnaNaknada;
    private double ukupanIznos;
    private int idOperater;
    private int idKlijent;
    private List<StavkaIznajmljivanja> stavke;

    public Iznajmljivanje(int idIznajmljivanje, LocalDate datumIzdavanja, double fiksnaNaknada, double ukupanIznos, int idOperater, int idKlijent) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datumIzdavanja = datumIzdavanja;
        this.fiksnaNaknada = fiksnaNaknada;
        this.ukupanIznos = ukupanIznos;
        this.idOperater = idOperater;
        this.idKlijent = idKlijent;
    }

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public LocalDate getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(LocalDate datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    public double getFiksnaNaknada() {
        return fiksnaNaknada;
    }

    public void setFiksnaNaknada(double fiksnaNaknada) {
        this.fiksnaNaknada = fiksnaNaknada;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public int getIdOperater() {
        return idOperater;
    }

    public void setIdOperater(int idOperater) {
        this.idOperater = idOperater;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", datumIzdavanja=" + datumIzdavanja + ", ukupanIznos=" + ukupanIznos + ", idOperater=" + idOperater + ", idKlijent=" + idKlijent + '}';
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        return this.idIznajmljivanje == other.idIznajmljivanje;
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Iznajmljivanje i = new Iznajmljivanje();
            i.setIdIznajmljivanje(rs.getInt("iznajmljivanje.idIznajmljivanje"));
            i.setDatumIzdavanja(rs.getDate("iznajmljivanje.datumIzdavanja").toLocalDate());
            i.setFiksnaNaknada(rs.getDouble("iznajmljivanje.fiksnaNaknada"));
            i.setUkupanIznos(rs.getDouble("iznajmljivanje.ukupanIznos"));
            i.setIdOperater(rs.getInt("iznajmljivanje.idOperater"));
            i.setIdKlijent(rs.getInt("iznajmljivanje.idKlijent"));
            lista.add(i);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumIzdavanja,fiksnaNaknada,ukupanIznos,idOperater,idKlijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datumIzdavanja + "'," + fiksnaNaknada + "," + ukupanIznos + "," + idOperater + "," + idKlijent;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje=" + idIznajmljivanje;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Iznajmljivanje i = new Iznajmljivanje();
            i.setIdIznajmljivanje(rs.getInt("iznajmljivanje.idIznajmljivanje"));
            i.setDatumIzdavanja(rs.getDate("iznajmljivanje.datumIzdavanja").toLocalDate());
            i.setFiksnaNaknada(rs.getDouble("iznajmljivanje.fiksnaNaknada"));
            i.setUkupanIznos(rs.getDouble("iznajmljivanje.ukupanIznos"));
            i.setIdOperater(rs.getInt("iznajmljivanje.idOperater"));
            i.setIdKlijent(rs.getInt("iznajmljivanje.idKlijent"));
            return i;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumIzdavanja='" + datumIzdavanja + "', fiksnaNaknada=" + fiksnaNaknada + ", ukupanIznos=" + ukupanIznos + ", idOperater=" + idOperater + ", idKlijent=" + idKlijent;
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idIznajmljivanje = id;
    }
}
