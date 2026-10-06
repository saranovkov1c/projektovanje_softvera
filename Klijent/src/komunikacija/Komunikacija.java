package komunikacija;

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
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * 
 * 
 * @author SARA
 */
public class Komunikacija {

    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void konekcija() throws java.io.IOException {
        socket = new Socket("localhost", 9000);
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    
    public Operater prijaviOperater(String korisnickoIme, String sifra) throws Exception {
        Operater o = new Operater();
        o.setKorisnickoIme(korisnickoIme);
        o.setSifra(sifra);
        Zahtev zahtev = new Zahtev(Operacija.PRIJAVI_OPERATER, o);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        return (Operater) odg.getOdgovor();
    }

    public List<Operater> vratiListuSviOperater() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_OPERATER, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<Operater>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuSviOperater");
        }
    }

    
    public boolean ubaciKlijent(Klijent klijent) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_KLIJENT, klijent);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva ubaciKlijent");
        }
    }

    public boolean promeniKlijent(Klijent klijent) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_KLIJENT, klijent);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva promeniKlijent");
        }
    }

    public boolean obrisiKlijent(Klijent klijent) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENT, klijent);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva obrisiKlijent");
        }
    }

    public Klijent pretraziKlijent(int idKlijent) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_KLIJENT, idKlijent);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Klijent) {
            return (Klijent) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva pretraziKlijent");
        }
    }

    public List<Klijent> vratiListuKlijent(KlijentPretraga kriterijum) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_KLIJENT, kriterijum);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<Klijent>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuKlijent");
        }
    }

    public List<Klijent> vratiListuSviKlijent() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_KLIJENT, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<Klijent>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuSviKlijent");
        }
    }

    
    public boolean ubaciClanskaKartica(ClanskaKartica ck) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_CLANSKAKARTICA, ck);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva ubaciClanskaKartica");
        }
    }

    public boolean promeniClanskaKartica(ClanskaKartica ck) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_CLANSKAKARTICA, ck);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva promeniClanskaKartica");
        }
    }

    public List<ClanskaKartica> vratiListuSviClanskaKartica() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_CLANSKAKARTICA, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<ClanskaKartica>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuSviClanskaKartica");
        }
    }

    // ---------------- TerminSmene ----------------
    public boolean ubaciTerminSmene(TerminSmene ts) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_TERMINSMENE, ts);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva ubaciTerminSmene");
        }
    }

    public boolean promeniTerminSmene(TerminSmene ts) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_TERMINSMENE, ts);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva promeniTerminSmene");
        }
    }

    public List<TerminSmene> vratiListuSviTerminSmene() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_TERMINSMENE, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<TerminSmene>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuSviTerminSmene");
        }
    }

    
    public boolean ubaciOperaterTerminSmene(OperaterTerminSmene ots) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_OPERATERTERMINSMENE, ots);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva ubaciOperaterTerminSmene");
        }
    }

    public boolean promeniOperaterTerminSmene(OperaterTerminSmene ots) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_OPERATERTERMINSMENE, ots);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva promeniOperaterTerminSmene");
        }
    }

    public List<OperaterTerminSmene> vratiListuOperaterTerminSmene(int idOperater) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_OPERATERTERMINSMENE, idOperater);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<OperaterTerminSmene>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuOperaterTerminSmene");
        }
    }

    
    public boolean ubaciKonzola(Konzola k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_KONZOLA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva ubaciKonzola");
        }
    }

    public boolean promeniKonzola(Konzola k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_KONZOLA, k);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva promeniKonzola");
        }
    }

    public List<Konzola> vratiListuSviKonzola() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_SVI_KONZOLA, null);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<Konzola>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuSviKonzola");
        }
    }

    
    public boolean ubaciIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UBACI_IZNAJMLJIVANJE, iznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva ubaciIznajmljivanje");
        }
    }

    public boolean promeniIznajmljivanje(Iznajmljivanje iznajmljivanje) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PROMENI_IZNAJMLJIVANJE, iznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Boolean) {
            return (boolean) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva promeniIznajmljivanje");
        }
    }

    public Iznajmljivanje pretraziIznajmljivanje(int idIznajmljivanje) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_IZNAJMLJIVANJE, idIznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof Iznajmljivanje) {
            return (Iznajmljivanje) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva pretraziIznajmljivanje");
        }
    }

    public List<Iznajmljivanje> vratiListuIznajmljivanje(IznajmljivanjePretraga kriterijum) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_IZNAJMLJIVANJE, kriterijum);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<Iznajmljivanje>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuIznajmljivanje");
        }
    }

    public List<StavkaIznajmljivanja> vratiListuStavkaZaKonzolu(int idKonzola) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.VRATI_LISTU_STAVKA_ZA_KONZOLU, idKonzola);
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        if (odgovor.getOdgovor() instanceof List) {
            return (List<StavkaIznajmljivanja>) odgovor.getOdgovor();
        } else {
            throw new Exception("Greska prilikom poziva vratiListuStavkaZaKonzolu");
        }
    }
}
