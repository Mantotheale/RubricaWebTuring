import java.util.Objects;

public record Persona (
        String nome,
        String cognome,
        String indirizzo,
        String telefono,
        int eta
) {
    public Persona {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(cognome);
        Objects.requireNonNull(indirizzo);
        Objects.requireNonNull(telefono);
        if (eta < 0) {
            throw new IllegalArgumentException("L'età non può essere negativa");
        }
    }
}
