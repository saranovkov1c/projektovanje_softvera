/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author SARA
 */
public interface OpstiDomenskiObjekat extends Serializable{
    
     public String vratiNazivTabele();
     public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
     public String vratiKoloneZaUbacivanje();  
     public String vratiVrednostiZaUbacivanje();
     public String vratiPrimarniKljuc();  
     public OpstiDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception;
     public String vratiVrednostiZaIzmenu();  
     public void vratiGenerisaniKljuc(int id);
}
