package controler;

import coordinator.Coordinator;
import domen.Klijent;
import domen.Konzola;
import domen.Iznajmljivanje;
import domen.IznajmljivanjePretraga;
import forme.PretraziIznajmljivanjeForma;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import modelTabele.ModelTabeleIznajmljivanje;

/**
 * 
 * 
 * @author SARA
 */
public class PretraziIznajmljivanjeController {

    private final PretraziIznajmljivanjeForma forma;
    private final ModelTabeleIznajmljivanje model;
    private List<Klijent> klijenti;
    private List<Konzola> konzole;

    public PretraziIznajmljivanjeController(PretraziIznajmljivanjeForma forma) {
        this.forma = forma;
        this.model = new ModelTabeleIznajmljivanje();
        forma.getjTableIznajmljivanja().setModel(model);
        ucitajKlijenteIKonzole();
        addActionListeners();
        forma.setVisible(true);
    }

    private void ucitajKlijenteIKonzole() {
        try {
            forma.getjComboBoxKlijent().addItem(null);
            forma.getjComboBoxKonzola().addItem(null);
            klijenti = Komunikacija.getInstance().vratiListuSviKlijent();
            konzole = Komunikacija.getInstance().vratiListuSviKonzola();
            for (Klijent k : klijenti) {
                forma.getjComboBoxKlijent().addItem(k);
            }
            for (Konzola k : konzole) {
                forma.getjComboBoxKonzola().addItem(k);
            }
            model.setKlijenti(klijenti);
            model.setOperaterIme(Coordinator.getInstance().getUlogovani().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Greska pri ucitavanju klijenata i konzola.");
        }
    }

    private void addActionListeners() {
        forma.getjButtonPronadji().addActionListener(e -> pretrazi());
        forma.getjButtonIzmeniIznajmljivanje().addActionListener(e -> izmeniIznajmljivanje());
        forma.getjButtonPrikaziDetalje().addActionListener(e -> prikaziIznajmljivanje());
    }

    private void pretrazi() {
        try {
            IznajmljivanjePretraga kriterijum = new IznajmljivanjePretraga();

            Klijent klijent = (Klijent) forma.getjComboBoxKlijent().getSelectedItem();
            if (klijent != null) {
                kriterijum.setIdKlijent(klijent.getIdKlijent());
            }

            Konzola konzola = (Konzola) forma.getjComboBoxKonzola().getSelectedItem();
            if (konzola != null) {
                kriterijum.setIdKonzola(konzola.getIdKonzola());
            }

            String datumOd = forma.getjTextFieldDatumOd().getText().trim();
            if (!datumOd.isEmpty()) {
                kriterijum.setDatumOd(LocalDate.parse(datumOd));
            }
            String datumDo = forma.getjTextFieldDatumDo().getText().trim();
            if (!datumDo.isEmpty()) {
                kriterijum.setDatumDo(LocalDate.parse(datumDo));
            }

            String iznosOd = forma.getjTextFieldIznosOd().getText().trim();
            if (!iznosOd.isEmpty()) {
                kriterijum.setIznosOd(Double.valueOf(iznosOd));
            }
            String iznosDo = forma.getjTextFieldIznosDo().getText().trim();
            if (!iznosDo.isEmpty()) {
                kriterijum.setIznosDo(Double.valueOf(iznosDo));
            }

            kriterijum.setIdOperater(Coordinator.getInstance().getUlogovani().getIdOperater());

            List<Iznajmljivanje> rezultat = Komunikacija.getInstance().vratiListuIznajmljivanje(kriterijum);

            if (rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Систем не може да нађе изнајмљивања по задатим критеријумима.");
            } else {
                JOptionPane.showMessageDialog(forma, "Систем је нашао изнајмљивања по задатим критеријумима");
            }

            model.setIznajmljivanja(rezultat);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе изнајмљивања по задатим критеријумима.");
        }
    }

    private void izmeniIznajmljivanje() {
        int red = forma.getjTableIznajmljivanja().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Izaberite iznajmljivanje.");
            return;
        }
        int idIznajmljivanje = model.getIznajmljivanja().get(red).getIdIznajmljivanje();
        try {
            Iznajmljivanje iznajmljivanje = Komunikacija.getInstance().pretraziIznajmljivanje(idIznajmljivanje);
            JOptionPane.showMessageDialog(forma, "Систем је нашао изнајмљивање.");
            Coordinator.getInstance().otvoriPromeniIznajmljivanjeForma(iznajmljivanje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе изнајмљивање.");
        }
    }

    private void prikaziIznajmljivanje() {
        int red = forma.getjTableIznajmljivanja().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе изнајмљивање.");
            return;
        }
        int idIznajmljivanje = model.getIznajmljivanja().get(red).getIdIznajmljivanje();
        try {
            Iznajmljivanje iznajmljivanje = Komunikacija.getInstance().pretraziIznajmljivanje(idIznajmljivanje);
            JOptionPane.showMessageDialog(forma, "Систем је нашао изнајмљивање.");
            Coordinator.getInstance().otvoriPrikaziIznajmljivanjeForma(iznajmljivanje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе изнајмљивање.");
        }
    }

    public void osveziPretragu() {
        pretrazi();
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
