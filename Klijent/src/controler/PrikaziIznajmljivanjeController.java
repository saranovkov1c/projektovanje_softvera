package controler;

import coordinator.Coordinator;
import domen.Klijent;
import domen.Iznajmljivanje;
import forme.PrikaziIznajmljivanjeForma;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import modelTabele.ModelTabeleStavkaIznajmljivanja;

/**
 * 
 * 
 * @author SARA
 */
public class PrikaziIznajmljivanjeController {
    private final PrikaziIznajmljivanjeForma forma;
    private final Iznajmljivanje iznajmljivanje;
    private ModelTabeleStavkaIznajmljivanja modelStavke;
    private List<Klijent> klijenti;

    public PrikaziIznajmljivanjeController(PrikaziIznajmljivanjeForma forma, Iznajmljivanje iznajmljivanje) {
        this.forma = forma;
        this.iznajmljivanje = iznajmljivanje;
        this.modelStavke = new ModelTabeleStavkaIznajmljivanja();
        forma.getjTableStavke().setModel(modelStavke);
        ucitajKlijente();
        popuniPodatke();
        forma.getjButtonZatvori().addActionListener(e -> forma.dispose());
        forma.setVisible(true);
    }

    private void ucitajKlijente() {
        try {
            klijenti = Komunikacija.getInstance().vratiListuSviKlijent();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Greska pri ucitavanju klijenata.");
        }
    }

    private void popuniPodatke() {
        forma.getjTextFieldDatum().setText(iznajmljivanje.getDatumIzdavanja().toString());
        forma.getjTextFieldOperater().setText(Coordinator.getInstance().getUlogovani().toString());

        String klijentIme = "Nepoznat";
        if (klijenti != null) {
            for (Klijent k : klijenti) {
                if (k.getIdKlijent() == iznajmljivanje.getIdKlijent()) {
                    klijentIme = k.toString();
                    break;
                }
            }
        }
        forma.getjTextFieldKlijent().setText(klijentIme);
        forma.getjTextFieldFiksnaNaknada().setText(String.valueOf(iznajmljivanje.getFiksnaNaknada()));
        forma.getjTextFieldUkupanIznos().setText(String.valueOf(iznajmljivanje.getUkupanIznos()));
        modelStavke.setStavke(iznajmljivanje.getStavke());
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
