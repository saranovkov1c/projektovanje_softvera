package controler;

import domen.Klijent;
import forme.PrikaziKlijentForma;

/**
 * 
 * 
 * @author SARA
 */
public class PrikaziKlijentController {
    private final PrikaziKlijentForma forma;
    private final Klijent klijent;

    public PrikaziKlijentController(PrikaziKlijentForma forma, Klijent klijent) {
        this.forma = forma;
        this.klijent = klijent;
        popuniPodatke();
        forma.getjButtonZatvori().addActionListener(e -> forma.dispose());
        forma.setVisible(true);
    }

    private void popuniPodatke() {
        forma.getjTextFieldIme().setText(klijent.getIme());
        forma.getjTextFieldPrezime().setText(klijent.getPrezime());
        forma.getjTextFieldTelefon().setText(klijent.getTelefon());
        forma.getjTextFieldEmail().setText(klijent.getEmail());
        forma.getjTextFieldKorisnickoIme().setText(klijent.getKorisnickoIme());
        forma.getjTextFieldOmiljenaPlatforma().setText(klijent.getOmiljenaPlatforma());
        forma.getjTextFieldDatumRegistracije().setText(String.valueOf(klijent.getDatumRegistracije()));
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
