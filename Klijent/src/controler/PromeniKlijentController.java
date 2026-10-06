package controler;

import coordinator.Coordinator;
import domen.ClanskaKartica;
import domen.Klijent;
import forme.PromeniKlijentForma;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 * 
 * 
 * @author SARA
 */
public class PromeniKlijentController {

    private final PromeniKlijentForma forma;
    private final Klijent klijent;
    private List<ClanskaKartica> kartice;

    public PromeniKlijentController(PromeniKlijentForma forma, Klijent klijent) {
        this.forma = forma;
        this.klijent = klijent;
        ucitajKarticeIPodatke();
        addActionListeners();
        forma.setVisible(true);
    }

    private void ucitajKarticeIPodatke() {
        try {
            List<Klijent> postojeciKlijenti = Komunikacija.getInstance().vratiListuSviKlijent();
            Set<Integer> zauzeteKartice = new HashSet<>();
            for (Klijent k : postojeciKlijenti) {
                if (k.getIdKlijent() != klijent.getIdKlijent()) {
                    zauzeteKartice.add(k.getIdClanskaKartica());
                }
            }

            List<ClanskaKartica> sveKartice = Komunikacija.getInstance().vratiListuSviClanskaKartica();
            kartice = new ArrayList<>();
            for (ClanskaKartica ck : sveKartice) {
                if (!zauzeteKartice.contains(ck.getIdClanskaKartica())) {
                    kartice.add(ck);
                    forma.getjComboBoxClanskaKartica().addItem(ck);
                    if (ck.getIdClanskaKartica() == klijent.getIdClanskaKartica()) {
                        forma.getjComboBoxClanskaKartica().setSelectedItem(ck);
                    }
                }
            }

            forma.getjTextFieldIme().setText(klijent.getIme());
            forma.getjTextFieldPrezime().setText(klijent.getPrezime());
            forma.getjTextFieldTelefon().setText(klijent.getTelefon());
            forma.getjTextFieldEmail().setText(klijent.getEmail());
            forma.getjTextFieldOmiljenaPlatforma().setText(klijent.getOmiljenaPlatforma());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Greska pri ucitavanju podataka.");
        }
    }

    private void addActionListeners() {
        forma.getjButtonSacuvajIzmene().addActionListener(e -> sacuvajIzmene());
    }

    private void sacuvajIzmene() {
        try {
            String ime = forma.getjTextFieldIme().getText().trim();
            String prezime = forma.getjTextFieldPrezime().getText().trim();
            String telefon = forma.getjTextFieldTelefon().getText().trim();
            String email = forma.getjTextFieldEmail().getText().trim();

            if (ime.isEmpty() || prezime.isEmpty() || telefon.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sva polja (osim omiljene platforme) moraju biti popunjena.");
                return;
            }

            ClanskaKartica kartica = (ClanskaKartica) forma.getjComboBoxClanskaKartica().getSelectedItem();
            if (kartica == null) {
                JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti klijenta. Izaberite clansku karticu.");
                return;
            }

            klijent.setIme(ime);
            klijent.setPrezime(prezime);
            klijent.setTelefon(telefon);
            klijent.setEmail(email);
            klijent.setOmiljenaPlatforma(forma.getjTextFieldOmiljenaPlatforma().getText().trim());
            klijent.setIdClanskaKartica(kartica.getIdClanskaKartica());

            boolean uspeh = Komunikacija.getInstance().promeniKlijent(klijent);
            if (uspeh) {
                JOptionPane.showMessageDialog(forma, "Систем је запамтио клијента.");
                Coordinator.getInstance().osveziPretraguKlijenta();
                forma.dispose();
            } else {
                JOptionPane.showMessageDialog(forma, "Систем не може да запамти клијента.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Систем не може да запамти клијента.");
        }
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
