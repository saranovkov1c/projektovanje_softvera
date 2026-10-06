package controller;

import domen.ClanskaKartica;
import domen.Iznajmljivanje;
import domen.IznajmljivanjePretraga;
import domen.Klijent;
import domen.KlijentPretraga;
import domen.Konzola;
import domen.Operater;
import domen.OperaterTerminSmene;
import domen.StavkaIznajmljivanja;
import domen.TerminSmene;
import java.util.List;
import operacija.login.PrijaviOperaterSO;
import operacije.clanskakartica.PromeniClanskaKarticaSO;
import operacije.clanskakartica.UbaciClanskaKarticaSO;
import operacije.clanskakartica.VratiListuSviClanskaKarticaSO;
import operacije.iznajmljivanje.UbaciIznajmljivanjeSO;
import operacije.iznajmljivanje.PretraziIznajmljivanjeSO;
import operacije.iznajmljivanje.PromeniIznajmljivanjeSO;
import operacije.iznajmljivanje.VratiListuIznajmljivanjeSO;
import operacije.iznajmljivanje.VratiListuStavkaZaKonzoluSO;
import operacije.klijent.UbaciKlijentSO;
import operacije.klijent.ObrisiKlijentSO;
import operacije.klijent.PretraziKlijentSO;
import operacije.klijent.PromeniKlijentSO;
import operacije.klijent.VratiListuKlijentSO;
import operacije.klijent.VratiListuSviKlijentSO;
import operacije.konzola.PromeniKonzolaSO;
import operacije.konzola.UbaciKonzolaSO;
import operacije.konzola.VratiListuSviKonzolaSO;
import operacije.operater.VratiListuSviOperaterSO;
import operacije.operaterterminsmene.PromeniOperaterTerminSmeneSO;
import operacije.operaterterminsmene.UbaciOperaterTerminSmeneSO;
import operacije.operaterterminsmene.VratiListuOperaterTerminSmeneSO;
import operacije.terminsmene.PromeniTerminSmeneSO;
import operacije.terminsmene.UbaciTerminSmeneSO;
import operacije.terminsmene.VratiListuSviTerminSmeneSO;

/**
 *
 *
 * @author SARA
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Operater prijaviOperater(Operater o) throws Exception {
        PrijaviOperaterSO so = new PrijaviOperaterSO();
        so.izvrsi(o, null);
        return so.getOperater();
    }

    public List<Operater> vratiListuSviOperater() throws Exception {
        VratiListuSviOperaterSO so = new VratiListuSviOperaterSO();
        so.izvrsi(null, null);
        return so.getLista();
    }

    public boolean ubaciKlijent(Klijent klijent) {
        try {
            UbaciKlijentSO so = new UbaciKlijentSO();
            so.izvrsi(klijent, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean promeniKlijent(Klijent klijent) {
        try {
            PromeniKlijentSO so = new PromeniKlijentSO();
            so.izvrsi(klijent, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean obrisiKlijent(Klijent klijent) {
        try {
            ObrisiKlijentSO so = new ObrisiKlijentSO();
            so.izvrsi(klijent, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Klijent pretraziKlijent(int idKlijent) throws Exception {
        PretraziKlijentSO so = new PretraziKlijentSO();
        so.izvrsi(idKlijent, null);
        return so.getKlijent();
    }

    public List<Klijent> vratiListuKlijent(KlijentPretraga kriterijum) throws Exception {
        VratiListuKlijentSO so = new VratiListuKlijentSO();
        so.izvrsi(kriterijum, null);
        return so.getLista();
    }

    public List<Klijent> vratiListuSviKlijent() throws Exception {
        VratiListuSviKlijentSO so = new VratiListuSviKlijentSO();
        so.izvrsi(null, null);
        return so.getLista();
    }

    public boolean ubaciClanskaKartica(ClanskaKartica ck) {
        try {
            UbaciClanskaKarticaSO so = new UbaciClanskaKarticaSO();
            so.izvrsi(ck, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean promeniClanskaKartica(ClanskaKartica ck) {
        try {
            PromeniClanskaKarticaSO so = new PromeniClanskaKarticaSO();
            so.izvrsi(ck, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<ClanskaKartica> vratiListuSviClanskaKartica() throws Exception {
        VratiListuSviClanskaKarticaSO so = new VratiListuSviClanskaKarticaSO();
        so.izvrsi(null, null);
        return so.getLista();
    }

    public boolean ubaciTerminSmene(TerminSmene ts) {
        try {
            UbaciTerminSmeneSO so = new UbaciTerminSmeneSO();
            so.izvrsi(ts, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean promeniTerminSmene(TerminSmene ts) {
        try {
            PromeniTerminSmeneSO so = new PromeniTerminSmeneSO();
            so.izvrsi(ts, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TerminSmene> vratiListuSviTerminSmene() throws Exception {
        VratiListuSviTerminSmeneSO so = new VratiListuSviTerminSmeneSO();
        so.izvrsi(null, null);
        return so.getLista();
    }

    public boolean ubaciOperaterTerminSmene(OperaterTerminSmene ots) {
        try {
            UbaciOperaterTerminSmeneSO so = new UbaciOperaterTerminSmeneSO();
            so.izvrsi(ots, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean promeniOperaterTerminSmene(OperaterTerminSmene ots) {
        try {
            PromeniOperaterTerminSmeneSO so = new PromeniOperaterTerminSmeneSO();
            so.izvrsi(ots, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<OperaterTerminSmene> vratiListuOperaterTerminSmene(int idOperater) throws Exception {
        VratiListuOperaterTerminSmeneSO so = new VratiListuOperaterTerminSmeneSO();
        so.izvrsi(idOperater, null);
        return so.getLista();
    }

    public boolean ubaciKonzola(Konzola k) {
        try {
            UbaciKonzolaSO so = new UbaciKonzolaSO();
            so.izvrsi(k, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean promeniKonzola(Konzola k) {
        try {
            PromeniKonzolaSO so = new PromeniKonzolaSO();
            so.izvrsi(k, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Konzola> vratiListuSviKonzola() throws Exception {
        VratiListuSviKonzolaSO so = new VratiListuSviKonzolaSO();
        so.izvrsi(null, null);
        return so.getLista();
    }

    public boolean ubaciIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            UbaciIznajmljivanjeSO so = new UbaciIznajmljivanjeSO();
            so.izvrsi(iznajmljivanje, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean promeniIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        try {
            PromeniIznajmljivanjeSO so = new PromeniIznajmljivanjeSO();
            so.izvrsi(iznajmljivanje, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Iznajmljivanje pretraziIznajmljivanje(int idIznajmljivanje) throws Exception {
        PretraziIznajmljivanjeSO so = new PretraziIznajmljivanjeSO();
        so.izvrsi(idIznajmljivanje, null);
        return so.getIznajmljivanje();
    }

    public List<Iznajmljivanje> vratiListuIznajmljivanje(IznajmljivanjePretraga kriterijum) throws Exception {
        VratiListuIznajmljivanjeSO so = new VratiListuIznajmljivanjeSO();
        so.izvrsi(kriterijum, null);
        return so.getLista();
    }

    public List<StavkaIznajmljivanja> vratiListuStavkaZaKonzolu(int idKonzola) throws Exception {
        VratiListuStavkaZaKonzoluSO so = new VratiListuStavkaZaKonzoluSO();
        so.izvrsi(idKonzola, null);
        return so.getLista();
    }
}
