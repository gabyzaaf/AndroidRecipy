package zaafranigabriel.recipy;

import android.app.Activity;
import android.os.Bundle;
<<<<<<< HEAD
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import zaafranigabriel.recipy.WebService.ApiLocation;

=======
import android.view.Menu;
import android.view.MenuItem;

>>>>>>> 14969a384279795f32002dad1598bcd3f123f060
public class DisplayMap extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_map);
<<<<<<< HEAD
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



=======
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
>>>>>>> 14969a384279795f32002dad1598bcd3f123f060
