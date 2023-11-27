package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    /**
     * déclarations des attributs
     */
    private String userName;
    private String password;
    private String serverName;
    private String portNumber;
    private static String dbName;
    private static Connection connection;

    /**
     * Constructeur
     * @throws SQLException
     */
    private DBConnection() throws SQLException {

        //initialisation des attributs
        this.userName="root";
        this.password="";
        this.serverName="localhost";
        this.portNumber="3306";
        this.dbName = "testpersonne";

        // creation de la connection
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + dbName;

        //maj de la connection
        DBConnection.connection= DriverManager.getConnection(urlDB, connectionProps);

    }

    /**
     * méthode getConnection
     * @return
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {
        if(DBConnection.connection==null)
            new DBConnection();

        return DBConnection.connection;
    }

    /**
     * méthode setNomDB
     * @param nomDB
     * @throws SQLException
     */
    public static void setNomDB(String nomDB) throws SQLException {
        if(dbName!=null&&!nomDB.equals(dbName)){
            dbName=nomDB;
            connection=null;
        }
    }

    public static String getNomDB() {
        return dbName;
    }
}