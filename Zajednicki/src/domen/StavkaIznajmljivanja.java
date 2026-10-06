/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author SARA
 */
public class StavkaIznajmljivanja implements OpstiDomenskiObjekat, Serializable{
    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private LocalDateTime pocetakIznajmljivanja;
    private LocalDateTime krajIznajmljivanja;
    private int brojSati;
    private double cena;
    private double iznos;
    private Konzola konzola;

    public StavkaIznajmljivanja(Iznajmljivanje iznajmljivanje, int rb, LocalDateTime pocetakIznajmljivanja, LocalDateTime krajIznajmljivanja, int brojSati, double cena, double iznos, Konzola konzola) {
        this.iznajmljivanje = iznajmljivanje;
        this.rb = rb;
        this.pocetakIznajmljivanja = pocetakIznajmljivanja;
        this.krajIznajmljivanja = krajIznajmljivanja;
        this.brojSati = brojSati;
        this.cena = cena;
        this.iznos = iznos;
        this.konzola = konzola;
    }

    public StavkaIznajmljivanja() {
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public LocalDateTime getPocetakIznajmljivanja() {
        return pocetakIznajmljivanja;
    }

    public void setPocetakIznajmljivanja(LocalDateTime pocetakIznajmljivanja) {
        this.pocetakIznajmljivanja = pocetakIznajmljivanja;
    }

    public LocalDateTime getKrajIznajmljivanja() {
        return krajIznajmljivanja;
    }

    public void setKrajIznajmljivanja(LocalDateTime krajIznajmljivanja) {
        this.krajIznajmljivanja = krajIznajmljivanja;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Konzola getKonzola() {
        return konzola;
    }

    public void setKonzola(Konzola konzola) {
        this.konzola = konzola;
    }

    @Override
    public String toString() {
        return "StavkaIznajmljivanja{" + "iznajmljivanje=" + iznajmljivanje + ", rb=" + rb + ", pocetakIznajmljivanja=" + pocetakIznajmljivanja + ", krajIznajmljivanja=" + krajIznajmljivanja + ", brojSati=" + brojSati + ", cena=" + cena + ", iznos=" + iznos + ", konzola=" + konzola + '}';
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
        final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.iznajmljivanje, other.iznajmljivanje);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaiznajmljivanja";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Iznajmljivanje i = new Iznajmljivanje();
            i.setIdIznajmljivanje(rs.getInt("stavkaiznajmljivanja.idIznajmljivanje"));

            Konzola k = new Konzola();
            k.setIdKonzola(rs.getInt("stavkaiznajmljivanja.idKonzola"));

            StavkaIznajmljivanja si = new StavkaIznajmljivanja();
            si.setIznajmljivanje(i);
            si.setRb(rs.getInt("stavkaiznajmljivanja.rb"));
            si.setPocetakIznajmljivanja(rs.getTimestamp("stavkaiznajmljivanja.pocetakIznajmljivanja").toLocalDateTime());
            si.setKrajIznajmljivanja(rs.getTimestamp("stavkaiznajmljivanja.krajIznajmljivanja").toLocalDateTime());
            si.setBrojSati(rs.getInt("stavkaiznajmljivanja.brojSati"));
            si.setCena(rs.getDouble("stavkaiznajmljivanja.cena"));
            si.setIznos(rs.getDouble("stavkaiznajmljivanja.iznos"));
            si.setKonzola(k);

            lista.add(si);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idIznajmljivanje,rb,pocetakIznajmljivanja,krajIznajmljivanja,brojSati,cena,iznos,idKonzola";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return iznajmljivanje.getIdIznajmljivanje() + "," + rb + ",'" + pocetakIznajmljivanja + "','" + krajIznajmljivanja + "'," + brojSati + "," + cena + "," + iznos + "," + konzola.getIdKonzola();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaiznajmljivanja.idIznajmljivanje=" + iznajmljivanje.getIdIznajmljivanje() + " AND stavkaiznajmljivanja.rb=" + rb;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Iznajmljivanje i = new Iznajmljivanje();
            i.setIdIznajmljivanje(rs.getInt("stavkaiznajmljivanja.idIznajmljivanje"));

            Konzola k = new Konzola();
            k.setIdKonzola(rs.getInt("stavkaiznajmljivanja.idKonzola"));

            StavkaIznajmljivanja si = new StavkaIznajmljivanja();
            si.setIznajmljivanje(i);
            si.setRb(rs.getInt("stavkaiznajmljivanja.rb"));
            si.setPocetakIznajmljivanja(rs.getTimestamp("stavkaiznajmljivanja.pocetakIznajmljivanja").toLocalDateTime());
            si.setKrajIznajmljivanja(rs.getTimestamp("stavkaiznajmljivanja.krajIznajmljivanja").toLocalDateTime());
            si.setBrojSati(rs.getInt("stavkaiznajmljivanja.brojSati"));
            si.setCena(rs.getDouble("stavkaiznajmljivanja.cena"));
            si.setIznos(rs.getDouble("stavkaiznajmljivanja.iznos"));
            si.setKonzola(k);

            return si;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "pocetakIznajmljivanja='" + pocetakIznajmljivanja + "', krajIznajmljivanja='" + krajIznajmljivanja + "', brojSati=" + brojSati + ", cena=" + cena + ", iznos=" + iznos + ", idKonzola=" + konzola.getIdKonzola();
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        //kompozitni primarni kljuc - nema auto-increment vrednost
    }
}
