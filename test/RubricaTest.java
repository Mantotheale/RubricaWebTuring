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
}