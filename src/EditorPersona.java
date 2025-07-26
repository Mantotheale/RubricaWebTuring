import javax.swing.*;
import java.util.Optional;

class EditorPersona extends JDialog {
    private final JTextField nome, cognome, indirizzo, telefono, eta;
    private Optional<Persona> risultatoDialog;

    public EditorPersona(JFrame parent, Persona persona) {
        super(parent, true);
        setTitle("Editor Persona");
        setSize(300, 250);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.risultatoDialog = Optional.empty();

        nome = aggiungiCampo("Nome", persona != null ? persona.nome() : "");
        cognome = aggiungiCampo("Cognome", persona != null ? persona.cognome() : "");
        indirizzo = aggiungiCampo("Indirizzo", persona != null ? persona.indirizzo() : "");
        telefono = aggiungiCampo("Telefono", persona != null ? persona.telefono() : "");
        eta = aggiungiCampo("Eta", persona != null ? String.valueOf(persona.eta()) : "");

        BottoniEditorPersona bottoni = new BottoniEditorPersona(this);
        add(bottoni);
    }

    private JTextField aggiungiCampo(String nome, String valore) {
        JPanel pannello = new JPanel();
        JLabel label = new JLabel(nome);
        JTextField campo = new JTextField(valore, 15);
        pannello.add(label);
        pannello.add(campo);
        add(pannello);
        return campo;
    }

    private void invioForm() {
        try {
            this.risultatoDialog = Optional.of(new Persona(
                    nome.getText(),
                    cognome.getText(),
                    indirizzo.getText(),
                    telefono.getText(),
                    Integer.parseInt(eta.getText())
            ));
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore nei dati: " + ex.getMessage());
        }
    }

    private void annullaForm() {
        dispose();
    }

    public Optional<Persona> risultatoDialog() {
        return risultatoDialog;
    }

    private static class BottoniEditorPersona extends JPanel {
        public BottoniEditorPersona(EditorPersona parent) {
            JButton salva = new JButton("Salva");
            JButton annulla = new JButton("Annulla");
            this.add(salva);
            this.add(annulla);

            salva.addActionListener(_ -> parent.invioForm());
            annulla.addActionListener(_ -> parent.annullaForm());
        }
    }
}
