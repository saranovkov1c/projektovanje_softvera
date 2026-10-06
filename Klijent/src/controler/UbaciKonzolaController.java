package controler;

import domen.Konzola;
import forme.UbaciKonzolaForma;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 * 
 * 
 * @author SARA
 */
public class UbaciKonzolaController {
    private final UbaciKonzolaForma forma;

    public UbaciKonzolaController(UbaciKonzolaForma forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {
        forma.getjButtonDodaj().addActionListener(e -> dodaj());
    }

    private void dodaj() {
        try {
            Konzola k = new Konzola();
            k.setNaziv(forma.getjTextFieldNaziv().getText().trim());
            k.setProizvodjac(forma.getjTextFieldProizvodjac().getText().trim());
            k.setCenaPoSatu(Double.parseDouble(forma.getjTextFieldCenaPoSatu().getText().trim()));

            boolean uspeh = Komunikacija.getInstance().ubaciKonzola(k);
            if (uspeh) {
                JOptionPane.showMessageDialog(forma, "Sistem je zapamtio konzolu.");
                forma.dispose();
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti konzolu.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(forma, "Cena po satu mora biti broj.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne moze da zapamti konzolu.");
        }
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
