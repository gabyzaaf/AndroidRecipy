package zaafranigabriel.recipydesign.Class.Tools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zaafranigabriel on 18/06/2016.
 */
public class Utilities  {

    private String value;

    public Utilities(String value) {
        this.value = value;
    }

    public JSONObject getJson()
    {
        JSONObject jsonObject=null;
        try {
            jsonObject= new JSONObject(this.value);
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return jsonObject;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}