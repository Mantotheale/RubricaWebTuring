import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rubrica {
    private final Set<Persona> persone = new HashSet<>();

    public List<Persona> persone() {
        return persone.stream().toList();
    }

    public void aggiungiPersona(Persona persona) {
        if (persone.contains(persona)) {
            throw new IllegalArgumentException("Non puoi aggiungere alla rubrica una persona già esistente");
        }

        persone.add(persona);
    }
}
