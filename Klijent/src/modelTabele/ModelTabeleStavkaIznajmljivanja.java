package modelTabele;

import domen.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * 
 * 
 * @author SARA
 */
public class ModelTabeleStavkaIznajmljivanja extends AbstractTableModel {
    private List<StavkaIznajmljivanja> stavke;
    private String[] kolone = {"rb", "Konzola", "Pocetak", "Kraj", "Broj sati", "Cena/h", "Iznos"};
    private Class[] klase = {int.class, String.class, String.class, String.class, int.class, double.class, double.class};

    public ModelTabeleStavkaIznajmljivanja() {
        stavke = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja s = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getRb();
            case 1: return s.getKonzola() != null ? s.getKonzola().toString() : "N/A";
            case 2: return s.getPocetakIznajmljivanja() != null ? s.getPocetakIznajmljivanja().toString() : "";
            case 3: return s.getKrajIznajmljivanja() != null ? s.getKrajIznajmljivanja().toString() : "";
            case 4: return s.getBrojSati();
            case 5: return s.getCena();
            case 6: return s.getIznos();
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

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    public void dodajStavku(StavkaIznajmljivanja stavka) {
        stavke.add(stavka);
        fireTableRowsInserted(stavke.size() - 1, stavke.size() - 1);
    }

    public void obrisiStavku(int row) {
        if (row >= 0 && row < stavke.size()) {
            stavke.remove(row);
            for (int i = 0; i < stavke.size(); i++) {
                stavke.get(i).setRb(i + 1);
            }
            fireTableDataChanged();
        }
    }
}
