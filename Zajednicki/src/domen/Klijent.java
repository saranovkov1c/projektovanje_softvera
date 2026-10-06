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
public class Klijent implements OpstiDomenskiObjekat, Serializable{
    private int idKlijent;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private String sifra;
    private String korisnickoIme;
    private String omiljenaPlatforma;
    private LocalDate datumRegistracije;
    private LocalDate zabranaDo;
    private int idClanskaKartica;

    public Klijent(int idKlijent, String ime, String prezime, String telefon, String email, String sifra, String korisnickoIme, String omiljenaPlatforma, LocalDate datumRegistracije, LocalDate zabranaDo, int idClanskaKartica) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.sifra = sifra;
        this.korisnickoIme = korisnickoIme;
        this.omiljenaPlatforma = omiljenaPlatforma;
        this.datumRegistracije = datumRegistracije;
        this.zabranaDo = zabranaDo;
        this.idClanskaKartica = idClanskaKartica;
    }

    public Klijent() {
    }

    public Klijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getOmiljenaPlatforma() {
        return omiljenaPlatforma;
    }

    public void setOmiljenaPlatforma(String omiljenaPlatforma) {
        this.omiljenaPlatforma = omiljenaPlatforma;
    }

    public LocalDate getDatumRegistracije() {
        return datumRegistracije;
    }

    public void setDatumRegistracije(LocalDate datumRegistracije) {
        this.datumRegistracije = datumRegistracije;
    }

    public LocalDate getZabranaDo() {
        return zabranaDo;
    }

    public void setZabranaDo(LocalDate zabranaDo) {
        this.zabranaDo = zabranaDo;
    }

    public int getIdClanskaKartica() {
        return idClanskaKartica;
    }

    public void setIdClanskaKartica(int idClanskaKartica) {
        this.idClanskaKartica = idClanskaKartica;
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
        final Klijent other = (Klijent) obj;
        return Objects.equals(this.korisnickoIme, other.korisnickoIme);
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("klijent.idKlijent");
            String ime2 = rs.getString("klijent.ime");
            String prezime2 = rs.getString("klijent.prezime");
            String telefon2 = rs.getString("klijent.telefon");
            String email2 = rs.getString("klijent.email");
            String sifra2 = rs.getString("klijent.sifra");
            String korisnickoIme2 = rs.getString("klijent.korisnickoIme");
            String omiljenaPlatforma2 = rs.getString("klijent.omiljenaPlatforma");
            LocalDate datumRegistracije2 = rs.getDate("klijent.datumRegistracije").toLocalDate();
            java.sql.Date zabranaDoSql = rs.getDate("klijent.zabranaDo");
            LocalDate zabranaDo2 = (zabranaDoSql != null) ? zabranaDoSql.toLocalDate() : null;
            int idClanskaKartica2 = rs.getInt("klijent.idClanskaKartica");

            Klijent k = new Klijent(id, ime2, prezime2, telefon2, email2, sifra2, korisnickoIme2, omiljenaPlatforma2, datumRegistracije2, zabranaDo2, idClanskaKartica2);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,telefon,email,sifra,korisnickoIme,omiljenaPlatforma,datumRegistracije,zabranaDo,idClanskaKartica";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        String zabranaDoSql = (zabranaDo != null) ? "'" + zabranaDo + "'" : "NULL";
        return "'" + ime + "','" + prezime + "','" + telefon + "','" + email + "','" + sifra + "','" + korisnickoIme + "','" + omiljenaPlatforma + "','" + datumRegistracije + "'," + zabranaDoSql + "," + idClanskaKartica;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "klijent.idKlijent=" + idKlijent;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int id = rs.getInt("klijent.idKlijent");
            String ime2 = rs.getString("klijent.ime");
            String prezime2 = rs.getString("klijent.prezime");
            String telefon2 = rs.getString("klijent.telefon");
            String email2 = rs.getString("klijent.email");
            String sifra2 = rs.getString("klijent.sifra");
            String korisnickoIme2 = rs.getString("klijent.korisnickoIme");
            String omiljenaPlatforma2 = rs.getString("klijent.omiljenaPlatforma");
            LocalDate datumRegistracije2 = rs.getDate("klijent.datumRegistracije").toLocalDate();
            java.sql.Date zabranaDoSql = rs.getDate("klijent.zabranaDo");
            LocalDate zabranaDo2 = (zabranaDoSql != null) ? zabranaDoSql.toLocalDate() : null;
            int idClanskaKartica2 = rs.getInt("klijent.idClanskaKartica");

            Klijent k = new Klijent(id, ime2, prezime2, telefon2, email2, sifra2, korisnickoIme2, omiljenaPlatforma2, datumRegistracije2, zabranaDo2, idClanskaKartica2);
            return k;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        String zabranaDoSql = (zabranaDo != null) ? "'" + zabranaDo + "'" : "NULL";
        return "ime='" + ime + "', prezime='" + prezime + "', telefon='" + telefon + "', email='" + email + "', sifra='" + sifra + "', korisnickoIme='" + korisnickoIme + "', omiljenaPlatforma='" + omiljenaPlatforma + "', datumRegistracije='" + datumRegistracije + "', zabranaDo=" + zabranaDoSql + ", idClanskaKartica=" + idClanskaKartica;
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idKlijent = id;
    }
}
