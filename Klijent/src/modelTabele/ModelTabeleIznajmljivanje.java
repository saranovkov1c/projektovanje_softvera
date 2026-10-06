package modelTabele;

import domen.Iznajmljivanje;
import domen.Klijent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * 
 * @author SARA
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {
    private List<Iznajmljivanje> iznajmljivanja;
    private List<Klijent> klijenti;
    private String operaterIme;
    private String[] kolone = {"ID", "Datum izdavanja", "Ukupan iznos", "Klijent", "Operater"};
    private Class[] klase = {int.class, LocalDate.class, double.class, String.class, String.class};

    public ModelTabeleIznajmljivanje() {
        iznajmljivanja = new ArrayList<>();
    }

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    public void setOperaterIme(String operaterIme) {
        this.operaterIme = operaterIme;
    }

    @Override
    public int getRowCount() {
        return iznajmljivanja.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje i = iznajmljivanja.get(rowIndex);
        switch (columnIndex) {
            case 0: return i.getIdIznajmljivanje();
            case 1: return i.getDatumIzdavanja();
            case 2: return i.getUkupanIznos();
            case 3:
                if (klijenti != null) {
                    for (Klijent k : klijenti) {
                        if (k.getIdKlijent() == i.getIdKlijent()) {
                            return k.toString();
                        }
                    }
                }
                return "Nepoznat klijent";
            case 4:
                return operaterIme != null ? operaterIme : "Nepoznat operater";
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return klase[columnIndex];
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }

    public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
        this.iznajmljivanja = iznajmljivanja;
        fireTableDataChanged();
    }
}
