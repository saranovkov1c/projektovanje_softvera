/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.OpstiDomenskiObjekat;
import domen.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 * 
 * 
 * @author SARA
 */
public class VratiListuStavkaZaKonzoluSO extends ApstraktnaGenerickaOperacija {
    private List<StavkaIznajmljivanja> lista;

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Integer)) {
            throw new Exception("Parametar mora biti ID konzole (Integer)");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        int idKonzola = (int) param;
        String uslov = "WHERE idKonzola = " + idKonzola;
        List<OpstiDomenskiObjekat> objekti = broker.getAll(new StavkaIznajmljivanja(), uslov);
        lista = new ArrayList<>();
        for (OpstiDomenskiObjekat odo : objekti) {
            lista.add((StavkaIznajmljivanja) odo);
        }
    }
}
