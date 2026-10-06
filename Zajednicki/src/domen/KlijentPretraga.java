package domen;

import java.io.Serializable;

/**
 * 
 *
 * @author SARA
 */
public class KlijentPretraga implements Serializable {

    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Integer idClanskaKartica;
    private String kategorijaKartice;

    public KlijentPretraga() {
    }

    public KlijentPretraga(String ime, String prezime, String email, String telefon, Integer idClanskaKartica) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.idClanskaKartica = idClanskaKartica;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Integer getIdClanskaKartica() {
        return idClanskaKartica;
    }

    public void setIdClanskaKartica(Integer idClanskaKartica) {
        this.idClanskaKartica = idClanskaKartica;
    }

    public String getKategorijaKartice() {
        return kategorijaKartice;
    }

    public void setKategorijaKartice(String kategorijaKartice) {
        this.kategorijaKartice = kategorijaKartice;
    }
}
