package zaafranigabriel.recipydesign.Class.Core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import zaafranigabriel.recipydesign.Exception.SendException;
import zaafranigabriel.recipydesign.Webservices.ApiLocation;
import zaafranigabriel.recipydesign.Webservices.WsCore;

/**
 * Created by zaafranigabriel on 18/06/2016.
 */
public class Recipy {

    private int id;
    private String title;
    private String content;
    private String adress;
    private int img;

    private double lat;
    private double lng;


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Recipy(int id,String title){
        this(id,title,"");
    }

    public Recipy(){

    }

    public Recipy(int id,String title,String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Recipy(String title, String content, String adress) {
        this.title = title;
        this.content = content;
        this.adress = adress;
    }

    public int getId(){
        return  this.id;
    }

    public String getAdress() {
        return adress;
    }
    public void setId(int v){
        this.id = v;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public Boolean add(User user)throws SendException{
        Boolean val = false;
        try {
            addAdresse();
            String value;
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSaddRecette.php");
            hashValue.put("id", String.valueOf(user.getId()));
            hashValue.put("title", title);
            hashValue.put("content", content);
            hashValue.put("adress", adress);
            hashValue.put("lat",String.valueOf(lat));
            hashValue.put("long",String.valueOf(lng));
            value = new WsCore().execute(hashValue).get();
            if (value.equals("true")) {
                val= true;
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e){
            throw new SendException(user.getId(),e.getMessage());
        }
        return val;
    }

    public JSONArray getTheRecette(){
        JSONArray json = null;
        try {
            String value;
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSgetTheRecette.php");
            hashValue.put("fid", String.valueOf(id));
            hashValue.put("title",title);
            value = new WsCore().execute(hashValue).get();
            json = WsCore.getJsonArray(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    public JSONArray getRecette(User user)throws SendException{
        JSONArray json = null;
        try {
            String value;
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSgetRecettes.php");
            hashValue.put("fid", String.valueOf(user.getId()));
            value = new WsCore().execute(hashValue).get();
            json = WsCore.getJsonArray(value);
        }catch(Exception e){
            throw new SendException(user.getId(),e.getMessage());
        }
        return json;
    }

    public Boolean deleteRecette() throws SendException{
       Boolean returnValue = false;

        try{
            String value;
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSdeleteRecette.php");
            hashValue.put("fid", String.valueOf(id));
            hashValue.put("title", title);
            value = new WsCore().execute(hashValue).get();
            if(value.contains("OK")){
                returnValue=  true;
            }
        }catch(Exception e){
            throw  new SendException(1,e.getMessage());
        }
        return returnValue;
    }

    private Boolean addAdresse(){
        Boolean state = true;
        try{
            String val = new ApiLocation().execute(adress).get();
            if(val.contains("ZERO_RESULTS")){
                state =false;
            }else{
                JSONObject jsonObject = new JSONObject(val);
                JSONArray listeResult = jsonObject.getJSONArray("results");
                JSONObject jsonObject1 = listeResult.getJSONObject(0);
                JSONObject geometry = jsonObject1.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                lat = location.getDouble("lat");
                lng = location.getDouble("lng");
            }
        }catch(Exception e){
            e.printStackTrace();

        }
        return state;

    }


    public JSONArray getLattitudeAndLongitude() throws  SendException{
       JSONArray array = null;
        try{
            String value;
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSgetLocation.php");
            hashValue.put("fid", String.valueOf(id));
            hashValue.put("title", title);
            value = new WsCore().execute(hashValue).get();
            array = WsCore.getJsonArray(value);
        }catch(Exception e){
            throw  new SendException(1,e.getMessage());
        }
        return array;
    }

    @Override
    public String toString() {
        return title;
    }
}
