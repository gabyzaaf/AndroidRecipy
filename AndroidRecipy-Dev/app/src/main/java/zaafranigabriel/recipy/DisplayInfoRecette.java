package zaafranigabriel.recipy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

import zaafranigabriel.recipy.Core.Recette;
import zaafranigabriel.recipy.WebService.ApiLocation;
import zaafranigabriel.recipy.WebService.MapsActivity;

/**
 * Created by HP 750-240nf on 16/06/2016.
 */
public class DisplayInfoRecette extends Activity implements View.OnClickListener {

    TextView lblTitle;
    TextView lblContent;
    TextView lblAdresse;

    Button btnAfficheMap;
    Recette recette;
    ListView list;

    double lat, lng;
    String title, content, adresse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_recette);
        String itemTitle = getIntent().getExtras().getString("titleRecette");
        lblTitle = (TextView) findViewById(R.id.lblTitleRecette);
        lblContent = (TextView) findViewById(R.id.lblContentRecette);
        lblAdresse = (TextView) findViewById(R.id.lblAdresseRecette);
        lblTitle.setText(itemTitle);


        try {
            list = (ListView) findViewById(R.id.listView);
            recette = new Recette();
            JSONArray object = recette.getRecetteInfo(Menu_Recipy.user.getId(), itemTitle);
            JSONObject jsonobject = object.getJSONObject(0);
            int id = Integer.parseInt(jsonobject.getString("id"));
            title = jsonobject.getString("title");
            content = jsonobject.getString("contenu");
            adresse = jsonobject.getString("adresseRecette");
            //adresse = "8 rue du vieil armand 95200 sarcelles";
            lblContent.setText(content);
            lblAdresse.setText(adresse);

        }catch(Exception e){
            e.printStackTrace();
        }

        btnAfficheMap = (Button)findViewById(R.id.btnDisplayMap);
        btnAfficheMap.setOnClickListener(this);
        try{
            String val = new ApiLocation().execute(adresse).get();
            //String val = new ApiLocation().execute("8 rue du vieil armand 95200 sarcelles").get();
            JSONObject jsonObject = new JSONObject(val);
            JSONArray listeResult = jsonObject.getJSONArray("results");
            JSONObject jsonObject1 = listeResult.getJSONObject(0);
            JSONObject geometry = jsonObject1.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            lat = location.getDouble("lat");
            lng = location.getDouble("lng");
        }catch(Exception e){
            //Log.d("ElementID",val2);
        }

    }

    @Override
    public void onClick(View v) {
        if(adresse != "" && adresse != null && adresse != "null") {
            Intent intent = new Intent(DisplayInfoRecette.this, MapsActivity.class);
            intent.putExtra("Latitude", lat);
            intent.putExtra("Longitude", lng);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Aucune adresse pour cette recette", Toast.LENGTH_SHORT).show();
        }
    }
}
