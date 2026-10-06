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
public class OperaterTerminSmene implements OpstiDomenskiObjekat, Serializable{
    private Operater operater;
    private TerminSmene terminSmene;
    private LocalDate datum;
    private int brojSati;
    private boolean potvrdjeno;

    public OperaterTerminSmene(Operater operater, TerminSmene terminSmene, LocalDate datum, int brojSati, boolean potvrdjeno) {
        this.operater = operater;
        this.terminSmene = terminSmene;
        this.datum = datum;
        this.brojSati = brojSati;
        this.potvrdjeno = potvrdjeno;
    }

    public OperaterTerminSmene() {
    }

    public Operater getOperater() {
        return operater;
    }

    public void setOperater(Operater operater) {
        this.operater = operater;
    }

    public TerminSmene getTerminSmene() {
        return terminSmene;
    }

    public void setTerminSmene(TerminSmene terminSmene) {
        this.terminSmene = terminSmene;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public boolean isPotvrdjeno() {
        return potvrdjeno;
    }

    public void setPotvrdjeno(boolean potvrdjeno) {
        this.potvrdjeno = potvrdjeno;
    }

    @Override
    public String toString() {
        return "OperaterTerminSmene{" + "operater=" + operater + ", terminSmene=" + terminSmene + ", datum=" + datum + ", brojSati=" + brojSati + ", potvrdjeno=" + potvrdjeno + '}';
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
        final OperaterTerminSmene other = (OperaterTerminSmene) obj;
        if (!Objects.equals(this.operater, other.operater)) {
            return false;
        }
        if (!Objects.equals(this.terminSmene, other.terminSmene)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    @Override
    public String vratiNazivTabele() {
        return "operater_terminsmene";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Operater o = new Operater();
            o.setIdOperater(rs.getInt("operaterterminsmene.idOperater"));

            TerminSmene ts = new TerminSmene();
            ts.setIdTerminSmene(rs.getInt("operaterterminsmene.idTerminSmene"));

            LocalDate datum2 = rs.getDate("operaterterminsmene.datum").toLocalDate();
            int brojSati2 = rs.getInt("operaterterminsmene.brojSati");
            boolean potvrdjeno2 = rs.getBoolean("operaterterminsmene.potvrdjeno");

            OperaterTerminSmene ots = new OperaterTerminSmene(o, ts, datum2, brojSati2, potvrdjeno2);
            lista.add(ots);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idOperater,idTerminSmene,datum,brojSati,potvrdjeno";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return operater.getIdOperater() + "," + terminSmene.getIdTerminSmene() + ",'" + datum + "'," + brojSati + "," + potvrdjeno;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "operaterterminsmene.idOperater=" + operater.getIdOperater() + " AND operaterterminsmene.idTerminSmene=" + terminSmene.getIdTerminSmene() + " AND operaterterminsmene.datum='" + datum + "'";
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            Operater o = new Operater();
            o.setIdOperater(rs.getInt("operaterterminsmene.idOperater"));

            TerminSmene ts = new TerminSmene();
            ts.setIdTerminSmene(rs.getInt("operaterterminsmene.idTerminSmene"));

            LocalDate datum2 = rs.getDate("operaterterminsmene.datum").toLocalDate();
            int brojSati2 = rs.getInt("operaterterminsmene.brojSati");
            boolean potvrdjeno2 = rs.getBoolean("operaterterminsmene.potvrdjeno");

            OperaterTerminSmene ots = new OperaterTerminSmene(o, ts, datum2, brojSati2, potvrdjeno2);
            return ots;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "brojSati=" + brojSati + ", potvrdjeno=" + potvrdjeno;
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        //kompozitni primarni kljuc - nema auto-increment vrednost
    }
}
