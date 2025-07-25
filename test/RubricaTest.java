import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RubricaTest {
    @Test
    void aggiungiPersonaTest() {
        Rubrica rubrica = new Rubrica();

        Persona persona = new Persona(
                "Alessio",
                "Mantonico",
                "Via Prova X, 12, Milano",
                "1234567890",
                23
        );

        rubrica.aggiungiPersona(persona);

        assertEquals(List.of(persona), rubrica.persone());
    }

    @Test
    void modificaPersonaTest() {
        Rubrica rubrica = new Rubrica();

        Persona persona = new Persona(
                "Alessio",
                "Mantonico",
                "Via Prova X, 12, Milano",
                "1234567890",
                23
        );

        Persona personaModificata = new Persona(
                "Andrea",
                "Mantonico",
                "Via Prova X, 12, Milano",
                "0987654321",
                100
        );

        rubrica.aggiungiPersona(persona);
        rubrica.modificaPersona(persona, personaModificata);

        assertEquals(List.of(personaModificata), rubrica.persone());
    }
}