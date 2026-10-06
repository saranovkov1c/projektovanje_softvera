package controler;

import domen.ClanskaKartica;
import forme.UbaciClanskaKarticaForma;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 * 
 * 
 * @author SARA
 */
public class UbaciClanskaKarticaController {
    private final UbaciClanskaKarticaForma forma;

    public UbaciClanskaKarticaController(UbaciClanskaKarticaForma forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {
        forma.getjButtonDodaj().addActionListener(e -> dodaj());
    }

    private void dodaj() {
        try {
            ClanskaKartica ck = new ClanskaKartica();
            ck.setKategorija(forma.getjTextFieldKategorija().getText().trim());

            boolean uspeh = Komunikacija.getInstance().ubaciClanskaKartica(ck);
            if (uspeh) {
                JOptionPane.showMessageDialog(forma, "Sistem je zapamtio clansku karticu.");
                forma.dispose();
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti clansku karticu.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti clansku karticu.");
        }
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
