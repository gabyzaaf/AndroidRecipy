package zaafranigabriel.recipy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import zaafranigabriel.recipy.WebService.ApiLocation;

public class DisplayMap extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_map);
        String val2 = null;
        try{
            String val = new ApiLocation().execute("8 rue du vieil armand 95200 sarcelles").get();
            JSONObject jsonObject = new JSONObject(val);
            JSONArray listeResult = jsonObject.getJSONArray("results");
            JSONObject jsonObject1 = listeResult.getJSONObject(0);
            JSONObject geometry = jsonObject1.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            double lat = location.getDouble("lat");
            double lng = location.getDouble("lng");
            //JSONObject listElement  = listeResult.getJSONObject("location");
            //val2 = listElement.toString();

            String toto = "2";
        }catch(Exception e){
            Log.d("ElementID",val2);
        }

    }
}



