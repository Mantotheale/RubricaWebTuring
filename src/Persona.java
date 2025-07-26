import java.util.Objects;

public record Persona (
        String nome,
        String cognome,
        String indirizzo,
        String telefono,
        int eta
) {
    public Persona {
        if (Objects.requireNonNull(nome).isBlank()) {
            throw new IllegalArgumentException("Il nome dev'essere valorizzato");
        }

        if (Objects.requireNonNull(cognome).isBlank()) {
            throw new IllegalArgumentException("Il cognome dev'essere valorizzato");
        }

        if (Objects.requireNonNull(indirizzo).isBlank()) {
            throw new IllegalArgumentException("L'indirizzo dev'essere valorizzato");
        }

        if (Objects.requireNonNull(telefono).isBlank()) {
            throw new IllegalArgumentException("Il numero di telefono dev'essere valorizzato");
        }

        if (eta < 0) {
            throw new IllegalArgumentException("L'età non può essere negativa");
        }
    }
}
