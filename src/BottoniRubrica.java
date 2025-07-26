import javax.swing.*;
import java.util.Optional;

public class BottoniRubrica extends JPanel {
    public BottoniRubrica(JFrame parent, Rubrica rubrica, RubricaTableModel tableModel) {
        super();

        BottoneNuovo nuovo = new BottoneNuovo(parent, rubrica, tableModel);
        JButton modifica = new JButton("Modifica");
        JButton elimina = new JButton("Elimina");

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
}
