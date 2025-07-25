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

    @Test
    void eliminaPersonaTest() {
        Rubrica rubrica = new Rubrica();

        Persona persona1 = new Persona(
                "Alessio",
                "Mantonico",
                "Via Prova X, 12, Milano",
                "1234567890",
                23
        );

        Persona persona2 = new Persona(
                "Andrea",
                "Mantonico",
                "Via Prova X, 12, Milano",
                "0987654321",
                100
        );

        rubrica.aggiungiPersona(persona1);
        rubrica.aggiungiPersona(persona2);
        rubrica.eliminaPersona(persona1);

        assertEquals(List.of(persona2), rubrica.persone());
    }
}