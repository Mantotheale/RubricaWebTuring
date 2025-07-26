import javax.swing.table.AbstractTableModel;
import java.util.*;

public class Rubrica extends AbstractTableModel {
    private final List<Persona> persone = new ArrayList<>();

    public void aggiungiPersona(Persona persona) {
        persone.add(persona);
        fireTableDataChanged();
    }

    public void modificaPersona(int indice, Persona personaModificata) {
        persone.set(indice, personaModificata);
        fireTableDataChanged();
    }

    public void eliminaPersona(int indice) {
        persone.remove(indice);
        fireTableDataChanged();
    }

    /*public int size() {
        return persone.size();
    }*/

    public Persona getPersona(int indice) {
        return persone.get(indice);
    }

    private final static String[] colonne = { "Nome", "Cognome", "Telefono" };

    @Override
    public int getRowCount() {
        return persone.size();
    }

    @Override
    public int getColumnCount() {
        return colonne.length;
    }

    @Override
    public Object getValueAt(int r, int c) {
        Persona p = persone.get(r);
        return switch (c) {
            case 0 -> p.nome();
            case 1 -> p.cognome();
            case 2 -> p.telefono();
            default -> throw new IllegalArgumentException("Non ci sono altre colonne");
        };
    }

    @Override
    public String getColumnName(int c) {
        return colonne[c];
    }
}
