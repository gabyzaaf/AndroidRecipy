package zaafranigabriel.recipydesign.Class.Core;

import org.json.JSONObject;

import java.util.HashMap;

import zaafranigabriel.recipydesign.Class.Tools.Utilities;
import zaafranigabriel.recipydesign.Webservices.WsCore;

/**
 * Created by zaafranigabriel on 18/06/2016.
 */
public class User {

    private int id;
    private String login;
    private String password;
    private String nom;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int id,String login, String password){
        this(login,password);
        this.id = id;
    }

    public User(int id,String login){
        this(id,login,"");
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject getConnexion(){
        JSONObject jsonObject = null;
        try{
            HashMap<String,String> listValue = new HashMap<String, String>();
            //  http://10.211.55.4/database/WSconnect.php http://recipy.soleng.ovh
            listValue.put("url","http://10.211.55.4/database/WSconnect.php");
            listValue.put("login",this.login);
            listValue.put("pass", this.password);
            String valueReturned = new WsCore().execute(listValue).get();
            Utilities utilities = new Utilities(valueReturned);
            jsonObject = utilities.getJson();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }
}
