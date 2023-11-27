import JDBC.Personne;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPersonne {

    @Test
    public void test_01_constructeurPersonne() {
        Personne p = new Personne("Jean", "Pierre");
        assertEquals(-1, p.getId());
        assertEquals("Jean", p.getNom());
        assertEquals("Pierre", p.getPrenom());
    }
}
