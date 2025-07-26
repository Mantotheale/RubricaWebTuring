import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RubricaTableModel extends AbstractTableModel {
    private final static String[] colonne = { "Nome", "Cognome", "Telefono" };
    private final Rubrica rubrica;
    private List<Persona> persone;

    public RubricaTableModel(Rubrica rubrica) {
        this.rubrica = rubrica;
        persone = rubrica.persone();
    }

    public int getRowCount() {
        return persone.size();
    }

    public int getColumnCount() {
        return colonne.length;
    }

    public Object getValueAt(int r, int c) {
        Persona p = persone.get(r);
        return switch (c) {
            case 0 -> p.nome();
            case 1 -> p.cognome();
            case 2 -> p.telefono();
            default -> throw new IllegalArgumentException("Non ci sono altre colonne");
        };
    }

    public String getColumnName(int c) {
        return colonne[c];
    }

    @Override
    public void fireTableDataChanged() {
        persone = rubrica.persone();
        super.fireTableDataChanged();
    }
}