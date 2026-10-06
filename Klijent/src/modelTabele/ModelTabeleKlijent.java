package modelTabele;

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
public class ModelTabeleKlijent extends AbstractTableModel {
    private List<Klijent> klijenti;
    private String[] kolone = {"ID", "Ime", "Prezime", "Telefon", "Email", "Datum registracije"};
    private Class[] klase = {int.class, String.class, String.class, String.class, String.class, LocalDate.class};

    public ModelTabeleKlijent() {
        klijenti = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return klijenti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = klijenti.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getIdKlijent();
            case 1: return k.getIme();
            case 2: return k.getPrezime();
            case 3: return k.getTelefon();
            case 4: return k.getEmail();
            case 5: return k.getDatumRegistracije();
            default: return "N/A";
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

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
        fireTableDataChanged();
    }
}
