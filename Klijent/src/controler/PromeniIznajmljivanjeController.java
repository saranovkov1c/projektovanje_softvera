package controler;

import coordinator.Coordinator;
import domen.Klijent;
import domen.Konzola;
import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import forme.PromeniIznajmljivanjeForma;
import forme.UbaciStavkaIznajmljivanjaForma;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import modelTabele.ModelTabeleStavkaIznajmljivanja;

/**
 * 
 * 
 * @author SARA
 */
public class PromeniIznajmljivanjeController {

    private final PromeniIznajmljivanjeForma forma;
    private final Iznajmljivanje iznajmljivanje;
    private ModelTabeleStavkaIznajmljivanja modelStavke;
    private List<Klijent> klijenti;
    private List<Konzola> konzole;

    public PromeniIznajmljivanjeController(PromeniIznajmljivanjeForma forma, Iznajmljivanje iznajmljivanje) {
        this.forma = forma;
        this.iznajmljivanje = iznajmljivanje;
        this.modelStavke = new ModelTabeleStavkaIznajmljivanja();
        forma.getjTableStavke().setModel(modelStavke);
        ucitajListeIPodatke();
        addActionListeners();
        forma.setVisible(true);
    }

    private void ucitajListeIPodatke() {
        try {
            klijenti = Komunikacija.getInstance().vratiListuSviKlijent();
            konzole = Komunikacija.getInstance().vratiListuSviKonzola();
            for (Klijent k : klijenti) {
                forma.getjComboBoxKlijent().addItem(k);
                if (k.getIdKlijent() == iznajmljivanje.getIdKlijent()) {
                    forma.getjComboBoxKlijent().setSelectedItem(k);
                }
            }
            forma.getjTextFieldDatum().setText(iznajmljivanje.getDatumIzdavanja().toString());
            forma.getjTextFieldOperater().setText(Coordinator.getInstance().getUlogovani().getIme() + " "
                    + Coordinator.getInstance().getUlogovani().getPrezime());
            modelStavke.setStavke(iznajmljivanje.getStavke());
            izracunajUkupno();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Greska pri ucitavanju podataka.");
        }
    }

    private void addActionListeners() {
        forma.getjButtonDodajStavku().addActionListener(e -> dodajStavku());
        forma.getjButtonObrisiStavku().addActionListener(e -> obrisiStavku());
        forma.getjButtonIzmeniStavku().addActionListener(e -> izmeniStavku());
        forma.getjButtonSacuvajIzmene().addActionListener(e -> sacuvajIzmene());
    }

    private boolean postojiPreklapanje(StavkaIznajmljivanja nova, List<StavkaIznajmljivanja> postojece) {
        for (StavkaIznajmljivanja s : postojece) {
            boolean preklapaju = nova.getPocetakIznajmljivanja().isBefore(s.getKrajIznajmljivanja())
                    && s.getPocetakIznajmljivanja().isBefore(nova.getKrajIznajmljivanja());
            if (preklapaju) {
                return true;
            }
        }
        return false;
    }

    private void dodajStavku() {
        UbaciStavkaIznajmljivanjaForma dialog = new UbaciStavkaIznajmljivanjaForma(forma, true, konzole);
        UbaciStavkaIznajmljivanjaController dialogController = new UbaciStavkaIznajmljivanjaController(dialog);
        dialog.setVisible(true);
        StavkaIznajmljivanja nova = dialogController.getStavka();
        if (nova != null) {
            try {
                List<StavkaIznajmljivanja> zauzeteStavke = Komunikacija.getInstance().vratiListuStavkaZaKonzolu(nova.getKonzola().getIdKonzola());
                List<StavkaIznajmljivanja> sveZaProveru = new ArrayList<>();
                for (StavkaIznajmljivanja s : zauzeteStavke) {
                    if (s.getIznajmljivanje().getIdIznajmljivanje() != iznajmljivanje.getIdIznajmljivanje()) {
                        sveZaProveru.add(s);
                    }
                }
                sveZaProveru.addAll(modelStavke.getStavke());

                if (postojiPreklapanje(nova, sveZaProveru)) {
                    JOptionPane.showMessageDialog(forma, "Konzola je vec zauzeta u tom terminu.");
                } else {
                    nova.setRb(modelStavke.getRowCount() + 1);
                    modelStavke.dodajStavku(nova);
                    izracunajUkupno();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(forma, "Greska pri proveri zauzetosti konzole.");
            }
        }
        dialog.dispose();
    }

    private void obrisiStavku() {
        int red = forma.getjTableStavke().getSelectedRow();
        if (red >= 0) {
            modelStavke.obrisiStavku(red);
            izracunajUkupno();
        } else {
            JOptionPane.showMessageDialog(forma, "Izaberite stavku za brisanje.");
        }
    }

    private void izmeniStavku() {
        int red = forma.getjTableStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Izaberite stavku za izmenu.");
            return;
        }
        StavkaIznajmljivanja stara = modelStavke.getStavke().get(red);
        UbaciStavkaIznajmljivanjaForma dialog = new UbaciStavkaIznajmljivanjaForma(forma, true, konzole);

        dialog.getjComboBoxKonzola().setSelectedItem(stara.getKonzola());
        dialog.getjTextFieldPocetak().setText(String.valueOf(stara.getPocetakIznajmljivanja()));
        dialog.getjTextFieldKraj().setText(String.valueOf(stara.getKrajIznajmljivanja()));
        dialog.getjTextFieldBrojSati().setText(String.valueOf(stara.getBrojSati()));
        dialog.getjTextFieldCena().setText(String.valueOf(stara.getCena()));
        dialog.getjTextFieldIznos().setText(String.valueOf(stara.getIznos()));

        UbaciStavkaIznajmljivanjaController dialogController = new UbaciStavkaIznajmljivanjaController(dialog);
        dialog.setVisible(true);
        StavkaIznajmljivanja izmenjena = dialogController.getStavka();
        if (izmenjena != null) {
            try {
                List<StavkaIznajmljivanja> zauzeteStavke = Komunikacija.getInstance().vratiListuStavkaZaKonzolu(izmenjena.getKonzola().getIdKonzola());
                List<StavkaIznajmljivanja> sveZaProveru = new ArrayList<>();
                for (StavkaIznajmljivanja s : zauzeteStavke) {
                    if (s.getIznajmljivanje().getIdIznajmljivanje() != iznajmljivanje.getIdIznajmljivanje()) {
                        sveZaProveru.add(s);
                    }
                }
                for (int i = 0; i < modelStavke.getRowCount(); i++) {
                    if (i != red) {
                        sveZaProveru.add(modelStavke.getStavke().get(i));
                    }
                }

                if (postojiPreklapanje(izmenjena, sveZaProveru)) {
                    JOptionPane.showMessageDialog(forma, "Konzola je vec zauzeta u tom terminu.");
                } else {
                    izmenjena.setRb(stara.getRb());
                    modelStavke.getStavke().set(red, izmenjena);
                    modelStavke.fireTableDataChanged();
                    izracunajUkupno();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(forma, "Greska pri proveri zauzetosti konzole.");
            }
        }
        dialog.dispose();
    }

    private void izracunajUkupno() {
        double fiksnaNaknada = 150.0 * modelStavke.getRowCount();
        forma.getjTextFieldFiksnaNaknada().setText(String.valueOf(fiksnaNaknada));

        double ukupno = fiksnaNaknada;
        for (StavkaIznajmljivanja s : modelStavke.getStavke()) {
            ukupno += s.getIznos();
        }
        forma.getjTextFieldUkupanIznos().setText(String.valueOf(ukupno));
    }

    private void sacuvajIzmene() {
        try {
            if (modelStavke.getRowCount() == 0) {
                JOptionPane.showMessageDialog(forma, "Систем не може да запамти изнајмљивање.");
                return;
            }
            Klijent klijent = (Klijent) forma.getjComboBoxKlijent().getSelectedItem();
            if (klijent == null) {
                JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti iznajmljivanje. Izaberite klijenta.");
                return;
            }

            LocalDate datum;
            try {
                datum = LocalDate.parse(forma.getjTextFieldDatum().getText().trim());
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(forma, "Datum nije u ispravnom formatu (YYYY-MM-DD).");
                return;
            }

            double fiksnaNaknada = 0;
            String fiksnaTekst = forma.getjTextFieldFiksnaNaknada().getText().trim();
            if (!fiksnaTekst.isEmpty()) {
                fiksnaNaknada = Double.parseDouble(fiksnaTekst);
            }

            iznajmljivanje.setDatumIzdavanja(datum);
            iznajmljivanje.setFiksnaNaknada(fiksnaNaknada);
            iznajmljivanje.setIdKlijent(klijent.getIdKlijent());
            iznajmljivanje.setUkupanIznos(Double.parseDouble(forma.getjTextFieldUkupanIznos().getText().trim()));
            iznajmljivanje.setStavke(modelStavke.getStavke());

            boolean uspeh = Komunikacija.getInstance().promeniIznajmljivanje(iznajmljivanje);
            if (uspeh) {
                JOptionPane.showMessageDialog(forma, "Систем је запамтио изнајмљивање.");
                Coordinator.getInstance().osveziPretraguIznajmljivanje();
                forma.dispose();
            } else {
                JOptionPane.showMessageDialog(forma, "Систем не може да запамти изнајмљивање.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(forma, "Fiksna naknada i ukupan iznos moraju biti brojevi.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да запамти изнајмљивање.");
        }
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
