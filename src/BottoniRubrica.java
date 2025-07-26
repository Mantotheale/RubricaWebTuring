import javax.swing.*;
import java.util.Optional;

public class BottoniRubrica extends JPanel {
    public BottoniRubrica(JFrame parent, Rubrica rubrica, RubricaTableModel tableModel, JTable tabella) {
        super();

        BottoneNuovo nuovo = new BottoneNuovo(parent, rubrica, tableModel);
        JButton modifica = new BottoneModifica(parent, rubrica, tableModel, tabella);
        JButton elimina = new BottoneElimina(rubrica, tableModel, tabella);

        add(nuovo);
        add(modifica);
        add(elimina);
    }

    private static class BottoneNuovo extends JButton {
        public BottoneNuovo(JFrame frame, Rubrica rubrica, RubricaTableModel tableModel) {
            super("Nuovo");

            addActionListener(_ -> {
                EditorPersona editor = new EditorPersona(frame, null);
                editor.setVisible(true);

                Optional<Persona> risultatoDialog = editor.risultatoDialog();
                risultatoDialog.ifPresent(p -> {
                    rubrica.aggiungiPersona(p);
                    tableModel.fireTableDataChanged();
                });
            });
        }
    }

    private static class BottoneModifica extends JButton {
        public BottoneModifica(JFrame frame, Rubrica rubrica, RubricaTableModel tableModel, JTable tabella) {
            super("Modifica");

            addActionListener(_ -> {
                int rigaSelezionata = tabella.getSelectedRow();
                if (rigaSelezionata == -1) {
                    JOptionPane.showMessageDialog(this, "Seleziona una persona da modificare.");
                    return;
                }
                Persona personaSelezionata = tableModel.getRiga(rigaSelezionata);

                EditorPersona editor = new EditorPersona(frame, personaSelezionata);
                editor.setVisible(true);

                editor.risultatoDialog().ifPresent(p -> {
                    rubrica.modificaPersona(personaSelezionata, p);
                    tableModel.fireTableDataChanged();
                });
            });
        }
    }

    private static class BottoneElimina extends JButton {
        public BottoneElimina(Rubrica rubrica, RubricaTableModel tableModel, JTable tabella) {
            super("Elimina");

            addActionListener(_ -> {
                int rigaSelezionata = tabella.getSelectedRow();
                if (rigaSelezionata == -1) {
                    JOptionPane.showMessageDialog(this, "Seleziona una persona da eliminare.");
                    return;
                }
                Persona personaSelezionata = tableModel.getRiga(rigaSelezionata);

                String dialogText = "Eliminare la persona " + personaSelezionata.nome() + " " + personaSelezionata.cognome() + "?";
                if (JOptionPane.showConfirmDialog(this, dialogText) == JOptionPane.YES_OPTION) {
                    rubrica.eliminaPersona(personaSelezionata);
                    tableModel.fireTableDataChanged();
                }
            });
        }
    }
}
