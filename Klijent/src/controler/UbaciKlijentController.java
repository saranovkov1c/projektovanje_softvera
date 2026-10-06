package controler;

import domen.Klijent;
import domen.ClanskaKartica;
import forme.UbaciKlijentForma;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
public class UbaciKlijentController {

    private final UbaciKlijentForma forma;
    private List<ClanskaKartica> kartice;

    public UbaciKlijentController(UbaciKlijentForma forma) {
        this.forma = forma;
        ucitajKartice();
        addActionListeners();
        forma.setVisible(true);
    }

    private void ucitajKartice() {
        try {
            List<Klijent> postojeciKlijenti = Komunikacija.getInstance().vratiListuSviKlijent();
            Set<Integer> zauzeteKartice = new HashSet<>();
            for (Klijent k : postojeciKlijenti) {
                zauzeteKartice.add(k.getIdClanskaKartica());
            }

            List<ClanskaKartica> sveKartice = Komunikacija.getInstance().vratiListuSviClanskaKartica();
            kartice = new ArrayList<>();
            for (ClanskaKartica ck : sveKartice) {
                if (!zauzeteKartice.contains(ck.getIdClanskaKartica())) {
                    kartice.add(ck);
                    forma.getjComboBoxClanskaKartica().addItem(ck);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(forma, "Greska pri ucitavanju clanskih kartica.");
        }
    }

    private void addActionListeners() {
        forma.getjButtonSacuvaj().addActionListener(e -> sacuvajKlijenta());
    }

    private void sacuvajKlijenta() {
        try {
            String ime = forma.getjTextFieldIme().getText().trim();
            String prezime = forma.getjTextFieldPrezime().getText().trim();
            String telefon = forma.getjTextFieldTelefon().getText().trim();
            String email = forma.getjTextFieldEmail().getText().trim();
            String korisnickoIme = forma.getjTextFieldKorisnickoIme().getText().trim();
            String sifra = String.valueOf(forma.getjPasswordFieldSifra().getPassword());

            if (ime.isEmpty() || prezime.isEmpty() || telefon.isEmpty() || email.isEmpty()
                    || korisnickoIme.isEmpty() || sifra.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Систем не може да запамти клијента.");
                return;
            }

            ClanskaKartica kartica = (ClanskaKartica) forma.getjComboBoxClanskaKartica().getSelectedItem();
            if (kartica == null) {
                JOptionPane.showMessageDialog(forma, "Систем не може да запамти клијента.");
                return;
            }

            Klijent k = new Klijent();
            k.setIme(ime);
            k.setPrezime(prezime);
            k.setTelefon(telefon);
            k.setEmail(email);
            k.setKorisnickoIme(korisnickoIme);
            k.setSifra(sifra);
            k.setOmiljenaPlatforma(forma.getjTextFieldOmiljenaPlatforma().getText().trim());
            k.setDatumRegistracije(LocalDate.now());
            k.setIdClanskaKartica(kartica.getIdClanskaKartica());

            boolean uspeh = Komunikacija.getInstance().ubaciKlijent(k);
            if (uspeh) {
                JOptionPane.showMessageDialog(forma, "Систем је запамтио клијента.");
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
