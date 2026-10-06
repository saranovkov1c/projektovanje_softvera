package controler;

import coordinator.Coordinator;
import domen.ClanskaKartica;
import domen.Klijent;
import domen.KlijentPretraga;
import forme.PretragaKlijentForma;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import modelTabele.ModelTabeleKlijent;

/**
 * 
 * 
 * @author SARA
 */
public class PretragaKlijentController {

    private final PretragaKlijentForma forma;
    private final ModelTabeleKlijent model;

    public PretragaKlijentController(PretragaKlijentForma forma) {
        this.forma = forma;
        this.model = new ModelTabeleKlijent();
        forma.getjTableKlijenti().setModel(model);
        ucitajKartice();
        addActionListeners();
        forma.setVisible(true);
    }

    private void addActionListeners() {
        forma.getjButtonPronadji().addActionListener(e -> pretrazi());
        forma.getjButtonIzmeniKlijenta().addActionListener(e -> izmeniKlijenta());
        forma.getjButtonPrikaziDetalje().addActionListener(e -> prikaziKlijenta());
        forma.getjButtonObrisiKlijenta().addActionListener(e -> obrisiKlijenta());
    }

    private void pretrazi() {
        try {
            KlijentPretraga kriterijum = new KlijentPretraga();
            String ime = forma.getjTextFieldIme().getText().trim();
            if (!ime.isEmpty()) {
                kriterijum.setIme(ime);
            }
            String prezime = forma.getjTextFieldPrezime().getText().trim();
            if (!prezime.isEmpty()) {
                kriterijum.setPrezime(prezime);
            }
            String email = forma.getjTextFieldEmail().getText().trim();
            if (!email.isEmpty()) {
                kriterijum.setEmail(email);
            }
            String telefon = forma.getjTextFieldTelefon().getText().trim();
            if (!telefon.isEmpty()) {
                kriterijum.setTelefon(telefon);
            }
            String kategorija = (String) forma.getjComboBoxClanskaKartica().getSelectedItem();
            if (kategorija != null) {
                kriterijum.setKategorijaKartice(kategorija);
            }
            List<Klijent> rezultat = Komunikacija.getInstance().vratiListuKlijent(kriterijum);
            if (rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Систем не може да нађе клијенте по задатим критеријумима.");
            } else {
                JOptionPane.showMessageDialog(forma, "Систем је нашао клијенте по задатим критеријумима.");
            }
            model.setKlijenti(rezultat);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе клијенте по задатим критеријумима.");
        }
    }

    private void izmeniKlijenta() {
        int red = forma.getjTableKlijenti().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе клијента.");
            return;
        }
        int idKlijent = model.getKlijenti().get(red).getIdKlijent();
        try {
            Klijent k = Komunikacija.getInstance().pretraziKlijent(idKlijent);
            JOptionPane.showMessageDialog(forma, "Систем је нашао клијента.");
            Coordinator.getInstance().otvoriPromeniKlijentForma(k);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе клијента.");
        }
    }

    private void prikaziKlijenta() {
        int red = forma.getjTableKlijenti().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе клијента.");
            return;
        }
        int idKlijent = model.getKlijenti().get(red).getIdKlijent();
        try {
            Klijent k = Komunikacija.getInstance().pretraziKlijent(idKlijent);
            JOptionPane.showMessageDialog(forma, "Систем је нашао клијента.");
            Coordinator.getInstance().otvoriPrikaziKlijentForma(k);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да нађе клијента.");
        }
    }

    private void obrisiKlijenta() {
        int red = forma.getjTableKlijenti().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Izaberite klijenta.");
            return;
        }

        int potvrda = JOptionPane.showConfirmDialog(forma, "Da li sigurno zelite da obrisete ovog klijenta?",
                "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
        if (potvrda != JOptionPane.YES_OPTION) {
            return;
        }

        Klijent klijent = model.getKlijenti().get(red);
        try {
            boolean uspeh = Komunikacija.getInstance().obrisiKlijent(klijent);
            if (uspeh) {
                JOptionPane.showMessageDialog(forma, "Систем је обрисао клијента..");
                pretrazi();
            } else {
                JOptionPane.showMessageDialog(forma, "Систем не може да обрише клијента.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да обрише клијента.");
        }
    }

    private void ucitajKartice() {
        try {
            forma.getjComboBoxClanskaKartica().addItem(null);
            List<ClanskaKartica> kartice = Komunikacija.getInstance().vratiListuSviClanskaKartica();
            java.util.LinkedHashSet<String> kategorije = new java.util.LinkedHashSet<>();
            for (ClanskaKartica ck : kartice) {
                kategorije.add(ck.getKategorija());
            }
            for (String kategorija : kategorije) {
                forma.getjComboBoxClanskaKartica().addItem(kategorija);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Greska pri ucitavanju clanskih kartica.");
        }
    }

    public void osveziPretragu() {
        pretrazi();
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
