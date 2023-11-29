import JDBC.Personne;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPersonne {

    @Test
    public void test_01_constructeurPersonne() {
        Personne p = new Personne("Jean", "Pierre");
        assertEquals(-1, p.getId());
        assertEquals("Jean", p.getNom());
        assertEquals("Pierre", p.getPrenom());
    }

    @Test
    public void test_02_findAll() throws SQLException {
        Personne p1 = new Personne(1, "Spielberg", "Steven");
        Personne p2 = new Personne(2, "Scott", "Ridley");
        Personne p3 = new Personne(3, "Kubrick", "Stanley");
        Personne p4 = new Personne(4, "Fincher", "David");
        ArrayList<Personne> listeVerification = new ArrayList<Personne>();
        listeVerification.add(p1);
        listeVerification.add(p2);
        listeVerification.add(p3);
        listeVerification.add(p4);
        for (int i = 0; i < listeVerification.size(); i++) {
            assertEquals(listeVerification.get(i).getId(), Personne.findAll().get(i).getId());
            assertEquals(listeVerification.get(i).getNom(), Personne.findAll().get(i).getNom());
            assertEquals(listeVerification.get(i).getPrenom(), Personne.findAll().get(i).getPrenom());
        }
    }

    @Test
    public void test_03_findById() throws SQLException {
        Personne p2 = new Personne(2, "Scott", "Ridley");
        assertEquals(p2.getId(), Personne.findById(2).getId());
        assertEquals(p2.getNom(), Personne.findById(2).getNom());
        assertEquals(p2.getPrenom(), Personne.findById(2).getPrenom());
    }

    @Test
    public void test_04_findByName() throws SQLException {
        Personne p1 = new Personne(1, "Spielberg", "Steven");
        Personne p5 = new Personne(5, "Spielberg", "Anne");
        ArrayList<Personne> listeVerification = new ArrayList<Personne>();
        listeVerification.add(p1);
        listeVerification.add(p5);
        for (int i = 0; i < listeVerification.size(); i++) {
            assertEquals(listeVerification.get(i).getId(), Personne.findByName("Spielberg").get(i).getId());
            assertEquals(listeVerification.get(i).getNom(), Personne.findByName("Spielberg").get(i).getNom());
            assertEquals(listeVerification.get(i).getPrenom(), Personne.findByName("Spielberg").get(i).getPrenom());
        }
    }

    @Test
    public void test_05_save_saveNew() throws SQLException {
        Personne p6 = new Personne("Scorsese", "Martin");
        assertEquals(-1, p6.getId());
        p6.save();
        assertEquals(6, p6.getId());
    }

    @Test
    public void test_06_save_update() throws SQLException {
        Personne p6 = new Personne("Scorsese", "Martin");
        p6.setNom("Campbell");
        p6.save();
        assertEquals(6, Personne.findByName("Campbell").get(0).getId());
    }

    @Test
    public void test_07_delete() throws SQLException {

    }
}
