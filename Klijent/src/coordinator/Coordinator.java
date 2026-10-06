package coordinator;

import controler.GlavnaFormaController;
import controler.UbaciIznajmljivanjeController;
import controler.UbaciKlijentController;
import controler.LoginController;
import controler.PretragaKlijentController;
import controler.PretraziIznajmljivanjeController;
import controler.PrikaziIznajmljivanjeController;
import controler.PrikaziKlijentController;
import controler.PromeniIznajmljivanjeController;
import controler.PromeniKlijentController;
import controler.UbaciClanskaKarticaController;
import controler.UbaciKonzolaController;
import controler.UbaciOperaterTerminSmeneController;
import controler.UbaciTerminSmeneController;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Operater;
import forme.GlavnaForma;
import forme.UbaciIznajmljivanjeForma;
import forme.UbaciKlijentForma;
import forme.LoginForma;
import forme.PretragaKlijentForma;
import forme.PretraziIznajmljivanjeForma;
import forme.PrikaziIznajmljivanjeForma;
import forme.PrikaziKlijentForma;
import forme.PromeniIznajmljivanjeForma;
import forme.PromeniKlijentForma;
import forme.UbaciClanskaKarticaForma;
import forme.UbaciKonzolaForma;
import forme.UbaciOperaterTerminSmeneForma;
import forme.UbaciTerminSmeneForma;

/**
 *
 *
 * @author SARA
 */
public class Coordinator {

    private static Coordinator instance;
    private Operater ulogovani;

    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;

    private UbaciKlijentController ubaciKlijentController;
    private PromeniKlijentController promeniKlijentController;
    private PrikaziKlijentController prikaziKlijentController;
    private PretragaKlijentController pretragaKlijentController;

    private UbaciClanskaKarticaController ubaciClanskaKarticaController;
    private UbaciTerminSmeneController ubaciTerminSmeneController;
    private UbaciOperaterTerminSmeneController ubaciOperaterTerminSmeneController;
    private UbaciKonzolaController ubaciKonzolaController;

    private UbaciIznajmljivanjeController ubaciIznajmljivanjeController;
    private PromeniIznajmljivanjeController promeniIznajmljivanjeController;
    private PrikaziIznajmljivanjeController prikaziIznajmljivanjeController;
    private PretraziIznajmljivanjeController pretraziIznajmljivanjeController;

    private Coordinator() {
    }

    public static Coordinator getInstance() {
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }

    public Operater getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Operater ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void osveziPretraguKlijenta() {
        if (pretragaKlijentController != null) {
            pretragaKlijentController.osveziPretragu();
        }
    }

    public void osveziPretraguIznajmljivanje() {
        if (pretraziIznajmljivanjeController != null) {
            pretraziIznajmljivanjeController.osveziPretragu();
        }
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public void otvoriUbaciKlijentForma() {
        ubaciKlijentController = new UbaciKlijentController(new UbaciKlijentForma());
        ubaciKlijentController.otvoriFormu();
    }

    public void otvoriPromeniKlijentForma(Klijent klijent) {
        promeniKlijentController = new PromeniKlijentController(new PromeniKlijentForma(), klijent);
        promeniKlijentController.otvoriFormu();
    }

    public void otvoriPrikaziKlijentForma(Klijent klijent) {
        prikaziKlijentController = new PrikaziKlijentController(new PrikaziKlijentForma(), klijent);
        prikaziKlijentController.otvoriFormu();
    }

    public void otvoriPretragaKlijentForma() {
        pretragaKlijentController = new PretragaKlijentController(new PretragaKlijentForma());
        pretragaKlijentController.otvoriFormu();
    }

    public void otvoriUbaciClanskaKarticaForma() {
        ubaciClanskaKarticaController = new UbaciClanskaKarticaController(new UbaciClanskaKarticaForma());
        ubaciClanskaKarticaController.otvoriFormu();
    }

    public void otvoriUbaciTerminSmeneForma() {
        ubaciTerminSmeneController = new UbaciTerminSmeneController(new UbaciTerminSmeneForma());
        ubaciTerminSmeneController.otvoriFormu();
    }

    public void otvoriUbaciOperaterTerminSmeneForma() {
        ubaciOperaterTerminSmeneController = new UbaciOperaterTerminSmeneController(new UbaciOperaterTerminSmeneForma());
        ubaciOperaterTerminSmeneController.otvoriFormu();
    }

    public void otvoriUbaciKonzolaForma() {
        ubaciKonzolaController = new UbaciKonzolaController(new UbaciKonzolaForma());
        ubaciKonzolaController.otvoriFormu();
    }

    public void otvoriUbaciIznajmljivanjeForma() {
        ubaciIznajmljivanjeController = new UbaciIznajmljivanjeController(new UbaciIznajmljivanjeForma());
        ubaciIznajmljivanjeController.otvoriFormu();
    }

    public void otvoriPromeniIznajmljivanjeForma(Iznajmljivanje iznajmljivanje) {
        promeniIznajmljivanjeController = new PromeniIznajmljivanjeController(new PromeniIznajmljivanjeForma(), iznajmljivanje);
        promeniIznajmljivanjeController.otvoriFormu();
    }

    public void otvoriPrikaziIznajmljivanjeForma(Iznajmljivanje iznajmljivanje) {
        prikaziIznajmljivanjeController = new PrikaziIznajmljivanjeController(new PrikaziIznajmljivanjeForma(), iznajmljivanje);
        prikaziIznajmljivanjeController.otvoriFormu();
    }

    public void otvoriPretraziIznajmljivanjeForma() {
        pretraziIznajmljivanjeController = new PretraziIznajmljivanjeController(new PretraziIznajmljivanjeForma());
        pretraziIznajmljivanjeController.otvoriFormu();
    }
}
