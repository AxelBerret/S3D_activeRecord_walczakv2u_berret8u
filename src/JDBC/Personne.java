package JDBC;

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
     * @param pNom
     * @param pPrenom
     */
    public Personne(String pNom, String pPrenom) {
        this.id = -1;
        this.nom = pNom;
        this.prenom = pPrenom;
    }
}
