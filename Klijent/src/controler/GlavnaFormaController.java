package controler;

import coordinator.Coordinator;
import domen.Operater;
import forme.GlavnaForma;

/**
 * 
 * 
 * @author SARA
 */
public class GlavnaFormaController {
    private final GlavnaForma forma;

    public GlavnaFormaController(GlavnaForma forma) {
        this.forma = forma;
    }

    public void otvoriFormu() {
        Operater ulogovan = Coordinator.getInstance().getUlogovani();
        forma.setVisible(true);
        forma.getjLabelUlogovani().setText(ulogovan.getIme() + " " + ulogovan.getPrezime());
    }
}
