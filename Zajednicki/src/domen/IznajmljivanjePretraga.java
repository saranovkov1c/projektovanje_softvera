package domen;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 *
 * @author SARA
 */
public class IznajmljivanjePretraga implements Serializable {
    private Integer idOperater;
    private Integer idKlijent;
    private Integer idKonzola;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private Double iznosOd;
    private Double iznosDo;

    public IznajmljivanjePretraga() {
    }

    public IznajmljivanjePretraga(Integer idOperater, Integer idKlijent, Integer idKonzola, LocalDate datumOd, LocalDate datumDo, Double iznosOd, Double iznosDo) {
        this.idOperater = idOperater;
        this.idKlijent = idKlijent;
        this.idKonzola = idKonzola;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.iznosOd = iznosOd;
        this.iznosDo = iznosDo;
    }

    public Integer getIdOperater() {
        return idOperater;
    }

    public void setIdOperater(Integer idOperater) {
        this.idOperater = idOperater;
    }

    public Integer getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(Integer idKlijent) {
        this.idKlijent = idKlijent;
    }

    public Integer getIdKonzola() {
        return idKonzola;
    }

    public void setIdKonzola(Integer idKonzola) {
        this.idKonzola = idKonzola;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public Double getIznosOd() {
        return iznosOd;
    }

    public void setIznosOd(Double iznosOd) {
        this.iznosOd = iznosOd;
    }

    public Double getIznosDo() {
        return iznosDo;
    }

    public void setIznosDo(Double iznosDo) {
        this.iznosDo = iznosDo;
    }
}
