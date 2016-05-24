package zaafranigabriel.recipy.Core;

import java.util.LinkedList;

/**
 * Created by zaafranigabriel on 21/05/2016.
 */
public class User {

    public static User user ;


    private int id;
    private String nom;
    private String logins;
    private LinkedList<Recette> recette;

    public User(){

    }

    public User(int id, String logins, String nom) {
        this.id = id;
        this.logins = logins;
        this.nom = nom;
        user  = new User();
        user.setNom(nom);
        user.setId(id);
        user.setLogins(logins);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LinkedList<Recette> getRecette() {
        return recette;
    }

    public void setRecette(LinkedList<Recette> recette) {
        this.recette = recette;
    }
}
