package niti;

import controller.Controller;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 * 
 * 
 * @author SARA
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Zahtev zahtev = (Zahtev) primalac.primi();
                if (zahtev == null) {
                    System.out.println("Klijent prekinut. Zahtev null!");
                    prekini();
                    return;
                }
                Odgovor odgovor = new Odgovor();
                boolean uspesno;
                switch (zahtev.getOperacija()) {
                    case PRIJAVI_OPERATER:
                        Operater o = (Operater) zahtev.getParametar();
                        o = Controller.getInstance().prijaviOperater(o);
                        odgovor.setOdgovor(o);
                        break;
                    case VRATI_LISTU_SVI_OPERATER:
                        List<Operater> operateri = Controller.getInstance().vratiListuSviOperater();
                        odgovor.setOdgovor(operateri);
                        break;

                    case UBACI_KLIJENT:
                        Klijent noviKlijent = (Klijent) zahtev.getParametar();
                        uspesno = Controller.getInstance().ubaciKlijent(noviKlijent);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PROMENI_KLIJENT:
                        Klijent izmenjeniKlijent = (Klijent) zahtev.getParametar();
                        uspesno = Controller.getInstance().promeniKlijent(izmenjeniKlijent);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case OBRISI_KLIJENT:
                        Klijent klijentBrisanje = (Klijent) zahtev.getParametar();
                        uspesno = Controller.getInstance().obrisiKlijent(klijentBrisanje);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PRETRAZI_KLIJENT:
                        int idKlijent = (int) zahtev.getParametar();
                        Klijent klijentObj = Controller.getInstance().pretraziKlijent(idKlijent);
                        odgovor.setOdgovor(klijentObj);
                        break;
                    case VRATI_LISTU_KLIJENT:
                        KlijentPretraga kriterijumK = (KlijentPretraga) zahtev.getParametar();
                        List<Klijent> listaK = Controller.getInstance().vratiListuKlijent(kriterijumK);
                        odgovor.setOdgovor(listaK);
                        break;
                    case VRATI_LISTU_SVI_KLIJENT:
                        List<Klijent> sviKlijenti = Controller.getInstance().vratiListuSviKlijent();
                        odgovor.setOdgovor(sviKlijenti);
                        break;

                    case UBACI_CLANSKAKARTICA:
                        ClanskaKartica novaKartica = (ClanskaKartica) zahtev.getParametar();
                        uspesno = Controller.getInstance().ubaciClanskaKartica(novaKartica);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PROMENI_CLANSKAKARTICA:
                        ClanskaKartica izmenjenaKartica = (ClanskaKartica) zahtev.getParametar();
                        uspesno = Controller.getInstance().promeniClanskaKartica(izmenjenaKartica);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case VRATI_LISTU_SVI_CLANSKAKARTICA:
                        List<ClanskaKartica> sveKartice = Controller.getInstance().vratiListuSviClanskaKartica();
                        odgovor.setOdgovor(sveKartice);
                        break;

                    case UBACI_TERMINSMENE:
                        TerminSmene noviTermin = (TerminSmene) zahtev.getParametar();
                        uspesno = Controller.getInstance().ubaciTerminSmene(noviTermin);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PROMENI_TERMINSMENE:
                        TerminSmene izmenjeniTermin = (TerminSmene) zahtev.getParametar();
                        uspesno = Controller.getInstance().promeniTerminSmene(izmenjeniTermin);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case VRATI_LISTU_SVI_TERMINSMENE:
                        List<TerminSmene> sviTermini = Controller.getInstance().vratiListuSviTerminSmene();
                        odgovor.setOdgovor(sviTermini);
                        break;

                    case UBACI_OPERATERTERMINSMENE:
                        OperaterTerminSmene novaDodela = (OperaterTerminSmene) zahtev.getParametar();
                        uspesno = Controller.getInstance().ubaciOperaterTerminSmene(novaDodela);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PROMENI_OPERATERTERMINSMENE:
                        OperaterTerminSmene izmenjenaDodela = (OperaterTerminSmene) zahtev.getParametar();
                        uspesno = Controller.getInstance().promeniOperaterTerminSmene(izmenjenaDodela);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case VRATI_LISTU_OPERATERTERMINSMENE:
                        int idOperaterZaSmene = (int) zahtev.getParametar();
                        List<OperaterTerminSmene> listaSmena = Controller.getInstance().vratiListuOperaterTerminSmene(idOperaterZaSmene);
                        odgovor.setOdgovor(listaSmena);
                        break;

                    case UBACI_KONZOLA:
                        Konzola novaKonzola = (Konzola) zahtev.getParametar();
                        uspesno = Controller.getInstance().ubaciKonzola(novaKonzola);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PROMENI_KONZOLA:
                        Konzola izmenjenaKonzola = (Konzola) zahtev.getParametar();
                        uspesno = Controller.getInstance().promeniKonzola(izmenjenaKonzola);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case VRATI_LISTU_SVI_KONZOLA:
                        List<Konzola> sveKonzole = Controller.getInstance().vratiListuSviKonzola();
                        odgovor.setOdgovor(sveKonzole);
                        break;

                    case UBACI_IZNAJMLJIVANJE:
                        Iznajmljivanje novoIznajmljivanje = (Iznajmljivanje) zahtev.getParametar();
                        uspesno = Controller.getInstance().ubaciIznajmljivanje(novoIznajmljivanje);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PROMENI_IZNAJMLJIVANJE:
                        Iznajmljivanje izmenjenoIznajmljivanje = (Iznajmljivanje) zahtev.getParametar();
                        uspesno = Controller.getInstance().promeniIznajmljivanje(izmenjenoIznajmljivanje);
                        odgovor.setOdgovor(uspesno);
                        break;
                    case PRETRAZI_IZNAJMLJIVANJE:
                        int idIznajmljivanje = (int) zahtev.getParametar();
                        Iznajmljivanje iznajmljivanjeSaStavkama = Controller.getInstance().pretraziIznajmljivanje(idIznajmljivanje);
                        odgovor.setOdgovor(iznajmljivanjeSaStavkama);
                        break;
                    case VRATI_LISTU_IZNAJMLJIVANJE:
                        IznajmljivanjePretraga kriterijumI = (IznajmljivanjePretraga) zahtev.getParametar();
                        List<Iznajmljivanje> listaI = Controller.getInstance().vratiListuIznajmljivanje(kriterijumI);
                        odgovor.setOdgovor(listaI);
                        break;
                    case VRATI_LISTU_STAVKA_ZA_KONZOLU:
                        int idKonzolaZaProveru = (int) zahtev.getParametar();
                        List<StavkaIznajmljivanja> stavkeZaKonzolu = Controller.getInstance().vratiListuStavkaZaKonzolu(idKonzolaZaProveru);
                        odgovor.setOdgovor(stavkeZaKonzolu);
                        break;
                    default:
                        System.out.println("Nepoznata operacija: " + zahtev.getOperacija());
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
                System.out.println("Klijent diskonektovan (ili GRESKA):");
                ex.printStackTrace();
                prekini();
            }
        }
    }

    public void prekini() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
}
