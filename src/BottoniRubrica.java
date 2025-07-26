import javax.swing.*;
import java.util.Optional;

public class BottoniRubrica extends JPanel {
    public BottoniRubrica(JFrame parent, Rubrica rubrica, JTable tabella) {
        super();

        JToolBar toolBar = new JToolBar();
        BottoneNuovo nuovo = new BottoneNuovo(parent, rubrica);
        JButton modifica = new BottoneModifica(parent, rubrica, tabella);
        JButton elimina = new BottoneElimina(rubrica, tabella);

        toolBar.add(nuovo);
        toolBar.add(modifica);
        toolBar.add(elimina);
        add(toolBar);
    }

    private static class BottoneNuovo extends JButton {
        public BottoneNuovo(JFrame frame, Rubrica rubrica) {
            super("Nuovo");

            setToolTipText("Nuovo contatto");

            addActionListener(_ -> {
                EditorPersona editor = new EditorPersona(frame, null);
                editor.setVisible(true);

                Optional<Persona> risultatoDialog = editor.risultatoDialog();
                risultatoDialog.ifPresent(rubrica::aggiungiPersona);
            });
        }
    }

    private static class BottoneModifica extends JButton {
        public BottoneModifica(JFrame frame, Rubrica rubrica, JTable tabella) {
            super("Modifica");

            setToolTipText("Modifica contatto");

            addActionListener(_ -> {
                int rigaSelezionata = tabella.getSelectedRow();
                if (rigaSelezionata == -1) {
                    JOptionPane.showMessageDialog(this, "Seleziona una persona da modificare.");
                    return;
                }
                Persona personaSelezionata = rubrica.getPersona(rigaSelezionata);

                EditorPersona editor = new EditorPersona(frame, personaSelezionata);
                editor.setVisible(true);

                editor.risultatoDialog().ifPresent(p -> rubrica.modificaPersona(rigaSelezionata, p));
            });
        }
    }

    private static class BottoneElimina extends JButton {
        public BottoneElimina(Rubrica rubrica, JTable tabella) {
            super("Elimina");

            setToolTipText("Elimina contatto");

            addActionListener(_ -> {
                int rigaSelezionata = tabella.getSelectedRow();
                if (rigaSelezionata == -1) {
                    JOptionPane.showMessageDialog(this, "Seleziona una persona da eliminare.");
                    return;
                }
                Persona personaSelezionata = rubrica.getPersona(rigaSelezionata);

                String dialogText = "Eliminare la persona " + personaSelezionata.nome() + " " + personaSelezionata.cognome() + "?";
                if (JOptionPane.showConfirmDialog(this, dialogText) == JOptionPane.YES_OPTION) {
                    rubrica.eliminaPersona(rigaSelezionata);
                }
            });
        }
    }
}
