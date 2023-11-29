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
     * methode setNom de la classe Personne
     * modifie le nom courant par celui en parametres
     * @param pNom nom courant que l on souhaite avoir
     */
    public void setNom(String pNom) {this.nom = pNom;}

    /**
     * methode setPrenom de la classe Personne
     * modifie le prenom courant par celui en parametres
     * @param pPrenom prenom courant que l on souhaite avoir
     */
    public void setPrenom(String pPrenom) {this.prenom = pPrenom;}


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
        String SQL = "SELECT * FROM Personne WHERE id = "+pId+";";
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
        String SQL = "SELECT * FROM Personne WHERE nom LIKE '"+pNom+"';";
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
     * methode saveNew de la classe Personne
     * qui permet d enregistrer dans la base de donnees une nouvelle
     * personne
     * @throws SQLException
     */
    private void saveNew() throws SQLException {
        // creation de la connexion
        Connection connect = DBConnection.getConnection();
        // preparation de la requete SQL
        String SQL = "INSERT INTO Personne (nom, prenom) VALUES ("+this.nom+","+this.prenom+");";
        // recuperation de la derniere ligne ajoutee (auto increment)
        PreparedStatement ps = connect.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            this.id = rs.getInt(1);
        }
        ps.executeUpdate();
    }

    /**
     * methode update de la classe Personne
     * qui permet de modifier les informations d une personne dans
     * la base de donnees
     * @throws SQLException
     */
    private void update() throws SQLException {
        // creation de la connexion
        Connection connect = DBConnection.getConnection();
        // preparation de la requete SQL
        String SQL =
            "UPDATE Personne SET nom = '"+this.nom+"', prenom = '"+this.prenom+"' WHERE id = "+this.id+";";
        PreparedStatement ps = connect.prepareStatement(SQL);
        ps.execute();
    }

    /**
     * methpde save de la classe Personne
     * qui permet d utiliser soit la methode saveNew soit la methode
     * update en fonction de l identifiant de la personne
     * @throws SQLException
     */
    public void save() throws SQLException {
        if (this.id != -1) this.update();
        else this.saveNew();
    }

    /**
     * methode delete de la classe Personne
     * qui permet de supprimer une personne de la base de données
     * @throws SQLException
     */
    public void delete() throws SQLException {
        // creation de la connexion
        Connection connect = DBConnection.getConnection();
        // preparation de la requete SQL
        String SQL =
            "DELETE FROM Personne WHERE Nom LIKE '"+this.nom+"' and Prenom LIKE '"+this.prenom+"';";
        PreparedStatement ps = connect.prepareStatement(SQL);
        ps.execute();
    }
}