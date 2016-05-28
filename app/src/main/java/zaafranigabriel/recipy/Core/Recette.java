package zaafranigabriel.recipy.Core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

import zaafranigabriel.recipy.WebService.WsGeneral;

/**
 * Created by zaafranigabriel on 21/05/2016.
 */
public class Recette {

    private int id;
    private String title;
    private String content;


    public Recette(){}

    public Recette(int id,String title,String content){
        this.id = id;
        this.title = title;
        this.content = content;

    }

    public Recette(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONArray getRecette(int fid){
        JSONArray json = null;
        try {
            String value;
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSgetRecettes.php");
            hashValue.put("fid", String.valueOf(fid));
            value = new WsGeneral().execute(hashValue).get();
            json = WsGeneral.getJsonArray(value);
        }catch(Exception e){
            // create log
        }
        return json;
    }

    public Boolean deleteRecette(int fid,String title){
        try{
            String value;
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSdeleteRecette.php");
            hashValue.put("fid", String.valueOf(fid));
            hashValue.put("title", title);
            value = new WsGeneral().execute(hashValue).get();
            JSONObject json = WsGeneral.getObject(value);
             String etat = json.get("ETAT").toString();
            if(etat.contains("OK")){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return title;
    }
}
