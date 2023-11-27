package JDBC;

import java.util.*;
import java.sql.*;

public class Personne {

    /**
     * attribut id de la classe Personne
     * represente l identifiant de la personne
     */
    private int id;

    /**
     * attributs nom et prenom de la classe Personne
     * representent les noms et prenoms des personnes
     */
    private String nom, prenom;


    /**
     * constructeur qui cree des objets de type Personne
     * a partir des donnees passees en parametres
     * @param pNom nom que l on souhaite donner a la personne
     * @param pPrenom prenom que l on souhaite donner a la personne
     */
    public Personne(String pNom, String pPrenom) {
        this.id = -1;
        this.nom = pNom;
        this.prenom = pPrenom;
    }

    /**
     * 2e constructeur qui cree des objets de types Personne
     * a l aide des donnees passees en parametres
     * @param pId id que l on souhaite donner a la personne
     * @param pNom nom que l on souhaite donner a la personne
     * @param pPrenom prenom que l on souhaite donner a la personne
     */
    public Personne(int pId, String pNom, String pPrenom) {
        this.id = id;
        this.nom = pNom;
        this.prenom = pPrenom;
    }


    /**
     * methode getId de la classe Personne
     * @return attribut id
     */
    public int getId() {return this.id;}

    /**
     * methode getNom de la classe Personne
     * @return attribut nom
     */
    public String getNom() {return this.nom;}

    /**
     * methode getPrenom de la classe Personne
     * @return attribut prenom
     */
    public String getPrenom() {return this.prenom;}


    /**
     * methode findAll de la classe Personne
     * qui permet de donner la liste des personnes presentes dans la
     * base de donnees
     * @return la liste des personnes presentes dans la base de donnees
     * @throws SQLException
     */
    public static ArrayList<Personne> findAll() throws SQLException {
        // creation de la liste des personnes
        ArrayList<Personne> listePersonnes = new ArrayList<Personne>();
        // creation de la connexion
        Connection connect = DBConnection.getConnection();
        // preparation de le requete SQL
        String SQL = "SELECT * FROM Personne;";
        PreparedStatement ps = connect.prepareStatement(SQL);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        // parcours des resultats
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int id = rs.getInt("id");
            Personne p = new Personne(id, nom, prenom);
            listePersonnes.add(p);
        }
        return listePersonnes;
    }

    /**
     * methode findById de la classe Personne
     * qui permet de retrouver une personne par un id que
     * l on donne
     * @param pId id de la personne que l on cherche
     * @return la personne a l id transmis
     * @throws SQLException
     */
    public static Personne findById(int pId) throws SQLException {
        // creation de la personne que l on recherche
        Personne p = new Personne("inconnu", "inconnu");
        // creation de la connexion
        Connection connect = DBConnection.getConnection();
        // preparation de le requete SQL
        String SQL = "SELECT * FROM Personne WHERE id = pId;";
        PreparedStatement ps = connect.prepareStatement(SQL);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        // s il y a un resultat
        if (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int id = rs.getInt("id");
            p = new Personne(id, nom, prenom);
        }
        return p;
    }

    /**
     * methode findByName de la classe Personne
     * qui permet de retrouver une ou des personnes par un nom
     * que l on donne
     * @param pNom nom de la ou des personnes que l on cherche
     * @return la liste des personnes ayant ce nom
     * @throws SQLException
     */
    public static ArrayList<Personne> findByName(String pNom) throws SQLException {
        // creation de la liste des personnes
        ArrayList<Personne> listePersonnes = new ArrayList<Personne>();
        // creation de la connexion
        Connection connect = DBConnection.getConnection();
        // preparation de le requete SQL
        String SQL = "SELECT * FROM Personne WHERE nom = pNom;";
        PreparedStatement ps = connect.prepareStatement(SQL);
        ps.execute();
        ResultSet rs = ps.getResultSet();
        // parcours des resultats
        while (rs.next()) {
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int id = rs.getInt("id");
            Personne p = new Personne(id, nom, prenom);
            listePersonnes.add(p);
        }
        return listePersonnes;
    }
}