package controler;

import domen.Konzola;
import domen.StavkaIznajmljivanja;
import forme.UbaciStavkaIznajmljivanjaForma;
import javax.swing.JOptionPane;

/**
 * 
 * 
 * @author SARA
 */
public class UbaciStavkaIznajmljivanjaController {

    private final UbaciStavkaIznajmljivanjaForma forma;
    private StavkaIznajmljivanja stavka;

    public UbaciStavkaIznajmljivanjaController(UbaciStavkaIznajmljivanjaForma forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {
        forma.getjButtonDodajStavku().addActionListener(e -> potvrdi());
        forma.getjComboBoxKonzola().addActionListener(e -> preracunaj());
        forma.getjTextFieldKraj().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                izracunajBrojSatiIIznos();
            }
        });
    }

    private void preracunaj() {
        Konzola selected = (Konzola) forma.getjComboBoxKonzola().getSelectedItem();
        if (selected != null) {
            forma.getjTextFieldCena().setText(String.valueOf(selected.getCenaPoSatu()));
            azurirajIznos();
        }
    }

    private void izracunajBrojSatiIIznos() {
        try {
            String pocetakTekst = forma.getjTextFieldPocetak().getText().trim();
            String krajTekst = forma.getjTextFieldKraj().getText().trim();
            if (pocetakTekst.isEmpty() || krajTekst.isEmpty()) {
                return;
            }

            java.time.LocalDateTime pocetak = java.time.LocalDateTime.parse(pocetakTekst);
            java.time.LocalDateTime kraj = java.time.LocalDateTime.parse(krajTekst);

            if (!kraj.isAfter(pocetak)) {
                JOptionPane.showMessageDialog(forma, "Kraj mora biti posle pocetka.");
                return;
            }

            long minuti = java.time.Duration.between(pocetak, kraj).toMinutes();
            int brojSati = (int) Math.ceil(minuti / 60.0);

            forma.getjTextFieldBrojSati().setText(String.valueOf(brojSati));
            azurirajIznos();
        } catch (java.time.format.DateTimeParseException ex) {
            
        }
    }

    private void azurirajIznos() {
        try {
            int brojSati = Integer.parseInt(forma.getjTextFieldBrojSati().getText().trim());
            double cena = Double.parseDouble(forma.getjTextFieldCena().getText().trim());
            forma.getjTextFieldIznos().setText(String.valueOf(brojSati * cena));
        } catch (NumberFormatException ex) {
            forma.getjTextFieldIznos().setText("");
        }
    }

    private void potvrdi() {
        try {
            String pocetakTekst = forma.getjTextFieldPocetak().getText().trim();
            String krajTekst = forma.getjTextFieldKraj().getText().trim();
            String brojSatiTekst = forma.getjTextFieldBrojSati().getText().trim();
            String cenaTekst = forma.getjTextFieldCena().getText().trim();

            if (pocetakTekst.isEmpty() || krajTekst.isEmpty() || brojSatiTekst.isEmpty() || cenaTekst.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Popunite sva polja.");
                return;
            }

            Konzola konzola = (Konzola) forma.getjComboBoxKonzola().getSelectedItem();
            if (konzola == null) {
                JOptionPane.showMessageDialog(forma, "Izaberite konzolu.");
                return;
            }

            int brojSati = Integer.parseInt(brojSatiTekst);
            double cena = Double.parseDouble(cenaTekst);
            if (brojSati <= 0 || cena <= 0) {
                JOptionPane.showMessageDialog(forma, "Broj sati i cena moraju biti pozitivni.");
                return;
            }

            stavka = new StavkaIznajmljivanja();
            stavka.setKonzola(konzola);
            stavka.setPocetakIznajmljivanja(java.time.LocalDateTime.parse(pocetakTekst));
            stavka.setKrajIznajmljivanja(java.time.LocalDateTime.parse(krajTekst));
            stavka.setBrojSati(brojSati);
            stavka.setCena(cena);
            stavka.setIznos(brojSati * cena);

            forma.setVisible(false);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(forma, "Broj sati i cena moraju biti brojevi.");
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(forma, "Datum/vreme nije u ispravnom formatu (YYYY-MM-DDTHH:mm).");
        }
    }

    public StavkaIznajmljivanja getStavka() {
        return stavka;
    }
}
