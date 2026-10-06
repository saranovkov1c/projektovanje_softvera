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
import java.util.Objects;

/**
 *
 * @author SARA
 */
public class Operater implements OpstiDomenskiObjekat, Serializable{
    private int idOperater;
    private String ime;
    private String prezime;
    private String email;
    private LocalDate datumZaposlenja;
    private String korisnickoIme;
    private String sifra;

    public Operater(int idOperater, String ime, String prezime, String email, LocalDate datumZaposlenja, String korisnickoIme, String sifra) {
        this.idOperater = idOperater;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.datumZaposlenja = datumZaposlenja;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public Operater() {
    }

    public Operater(int idOperater) {
        this.idOperater = idOperater;
    }

    public int getIdOperater() {
        return idOperater;
    }

    public void setIdOperater(int idOperater) {
        this.idOperater = idOperater;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(LocalDate datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Operater other = (Operater) obj;
        return Objects.equals(this.korisnickoIme, other.korisnickoIme);
    }

    @Override
    public String vratiNazivTabele() {
        return "operater";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("operater.idOperater");
            String ime2 = rs.getString("operater.ime");
            String prezime2 = rs.getString("operater.prezime");
            String email2 = rs.getString("operater.email");
            LocalDate datumZaposlenja2 = rs.getDate("operater.datumZaposlenja").toLocalDate();
            String korisnickoIme2 = rs.getString("operater.korisnickoIme");
            String sifra2 = rs.getString("operater.sifra");

            Operater o = new Operater(id, ime2, prezime2, email2, datumZaposlenja2, korisnickoIme2, sifra2);
            lista.add(o);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,datumZaposlenja,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "','" + prezime + "','" + email + "','" + datumZaposlenja + "','" + korisnickoIme + "','" + sifra + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "operater.idOperater=" + idOperater;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int id = rs.getInt("operater.idOperater");
            String ime2 = rs.getString("operater.ime");
            String prezime2 = rs.getString("operater.prezime");
            String email2 = rs.getString("operater.email");
            LocalDate datumZaposlenja2 = rs.getDate("operater.datumZaposlenja").toLocalDate();
            String korisnickoIme2 = rs.getString("operater.korisnickoIme");
            String sifra2 = rs.getString("operater.sifra");

            Operater o = new Operater(id, ime2, prezime2, email2, datumZaposlenja2, korisnickoIme2, sifra2);
            return o;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='" + ime + "', prezime='" + prezime + "', email='" + email + "', datumZaposlenja='" + datumZaposlenja + "', korisnickoIme='" + korisnickoIme + "', sifra='" + sifra + "'";
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idOperater = id;
    }
}
