package controler;

import domen.TerminSmene;
import forme.UbaciTerminSmeneForma;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 * 
 * 
 * @author SARA
 */
public class UbaciTerminSmeneController {
    private final UbaciTerminSmeneForma forma;

    public UbaciTerminSmeneController(UbaciTerminSmeneForma forma) {
        this.forma = forma;
        addActionListeners();
    }

    private void addActionListeners() {
        forma.getjButtonDodaj().addActionListener(e -> dodaj());
    }

    private void dodaj() {
    try {
        String tipTermina = forma.getjTextFieldTipTermina().getText().trim();

        if (tipTermina.isEmpty()) {
            JOptionPane.showMessageDialog(forma, "Систем не може да запамти термин смене.");
            return;
        }

        TerminSmene ts = new TerminSmene();
        ts.setTipTermina(tipTermina);
        ts.setNapomena(forma.getjTextAreaNapomena().getText().trim());

        boolean uspeh = Komunikacija.getInstance().ubaciTerminSmene(ts);
        if (uspeh) {
            JOptionPane.showMessageDialog(forma, "Систем је запамтио термин смене.");
            forma.dispose();
        } else {
            JOptionPane.showMessageDialog(forma, "Систем не може да запамти термин смене.");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(forma, "Систем не може да запамти термин смене.");
    }
}

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
