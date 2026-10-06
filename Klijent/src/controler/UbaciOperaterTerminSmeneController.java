package controler;

import coordinator.Coordinator;
import domen.Operater;
import domen.OperaterTerminSmene;
import domen.TerminSmene;
import forme.UbaciOperaterTerminSmeneForma;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 * 
 * 
 * @author SARA
 */
public class UbaciOperaterTerminSmeneController {

    private final UbaciOperaterTerminSmeneForma forma;
    private List<TerminSmene> termini;

    public UbaciOperaterTerminSmeneController(UbaciOperaterTerminSmeneForma forma) {
        this.forma = forma;
        ucitajTermine();
        addActionListeners();
    }

    private void ucitajTermine() {
        try {
            termini = Komunikacija.getInstance().vratiListuSviTerminSmene();
            for (TerminSmene ts : termini) {
                forma.getjComboBoxTerminSmene().addItem(ts);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Greska pri ucitavanju termina smena.");
        }
    }

    private void addActionListeners() {
        forma.getjButtonDodaj().addActionListener(e -> dodaj());
    }

    private void dodaj() {
        try {
            TerminSmene termin = (TerminSmene) forma.getjComboBoxTerminSmene().getSelectedItem();
            if (termin == null) {
                JOptionPane.showMessageDialog(forma, "Izaberite termin smene.");
                return;
            }

            LocalDate datum;
            try {
                datum = LocalDate.parse(forma.getjTextFieldDatum().getText().trim());
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(forma, "Datum nije u ispravnom formatu (YYYY-MM-DD).");
                return;
            }

            if (!datum.isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(forma, "Datum mora biti u buducnosti.");
                return;
            }

            int brojSati = Integer.parseInt(forma.getjTextFieldBrojSati().getText().trim());
            boolean potvrdjeno = forma.getjCheckBoxPotvrdjeno().isSelected();

            Operater ulogovani = Coordinator.getInstance().getUlogovani();

            OperaterTerminSmene ots = new OperaterTerminSmene();
            ots.setOperater(ulogovani);
            ots.setTerminSmene(termin);
            ots.setDatum(datum);
            ots.setBrojSati(brojSati);
            ots.setPotvrdjeno(potvrdjeno);

            boolean uspeh = Komunikacija.getInstance().ubaciOperaterTerminSmene(ots);
            if (uspeh) {
                JOptionPane.showMessageDialog(forma, "Sistem je zapamtio dodelu termina.");
                forma.dispose();
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti dodelu termina.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(forma, "Broj sati mora biti ceo broj.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti dodelu termina.");
        }
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
