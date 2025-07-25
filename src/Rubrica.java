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

    public void modificaPersona(Persona persona, Persona personaModificata) {
        if (!persone.contains(persona)) {
            throw new IllegalArgumentException("Non è possibile modificare una persona non presente nella rubrica alla rubrica");
        }

        persone.remove(persona);
        persone.add(personaModificata);
    }

    public void eliminaPersona(Persona persona) {
        if (!persone.contains(persona)) {
            throw new IllegalArgumentException("Non è possibile eliminare una persona non presente nella rubrica alla rubrica");
        }

        persone.remove(persona);
    }
}
