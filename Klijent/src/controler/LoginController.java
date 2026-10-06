package controler;

import coordinator.Coordinator;
import domen.Operater;
import forme.LoginForma;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 * 
 * 
 * @author SARA
 */
public class LoginController {

    private final LoginForma forma;

    public LoginController(LoginForma forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {
        forma.getjButtonPrijava().addActionListener(e -> prijava());
    }

    private void prijava() {
        String korisnickoIme = forma.getjTextFieldKorisnickoIme().getText().trim();
        String sifra = String.valueOf(forma.getjPasswordFieldSifra().getPassword());

        try {
            Komunikacija.getInstance().konekcija();
            Operater o = Komunikacija.getInstance().prijaviOperater(korisnickoIme, sifra);

            if (o == null) {
                JOptionPane.showMessageDialog(forma, "Корисничко име и шифра нису исправни.", "Грешка", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Корисничко име и шифра су исправни.", "Успех", JOptionPane.INFORMATION_MESSAGE);
                Coordinator.getInstance().setUlogovani(o);
                Coordinator.getInstance().otvoriGlavnuFormu();
                forma.dispose();
            }
        } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(forma, "Server nije dostupan. Proveri da li je server pokrenut.", "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Doslo je do greske prilikom prijave.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
