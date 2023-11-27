import JDBC.DBConnection;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBConnectionTest {

    @Test
    public void test_ConnectionUnique() {
        try {
            Connection c1 = DBConnection.getConnection();
            Connection c2 = DBConnection.getConnection();
            assertSame(c1, c2);

        } catch (SQLException e) {
            fail("Erreur : " + e.getMessage());
        }
    }

    @Test
    public void test_ChangeBD() {
        try {
            Connection c1 = DBConnection.getConnection();
            String n1 = DBConnection.getNomDB();
            DBConnection.setNomDB("TestPersonne2");
            String n2 = DBConnection.getNomDB();
            Connection c2 = DBConnection.getConnection();
            assertNotSame(c1, c2);
            System.out.printf("c1 : %s\nc2 : %s\n", n1, n2);

        } catch (SQLException e) {
            fail("Erreur : " + e.getMessage());
        }
    }
}