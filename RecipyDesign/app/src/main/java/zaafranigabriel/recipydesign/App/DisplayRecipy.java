package zaafranigabriel.recipydesign.App;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zaafranigabriel.recipydesign.Class.Core.Recipy;
import zaafranigabriel.recipydesign.R;

/**
 * Created by zaafranigabriel on 19/06/2016.
 */
public class DisplayRecipy extends AppCompatActivity implements View.OnClickListener{

    private TextView Ttitle,Tcontent;
    private Button btnDelete;
    private Recipy recipy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_layout);
try {
    Ttitle = (TextView) findViewById(R.id.txt_title);
    Tcontent = (TextView) findViewById(R.id.txt_content);
    btnDelete = (Button) findViewById(R.id.btn_delete);
    btnDelete.setOnClickListener(this);

    Intent intent = getIntent();
    String recipyName = intent.getStringExtra("recipyName");
    int id = intent.getIntExtra("recipyId", 0);
    recipy = new Recipy(id, recipyName);
    JSONArray jsonArray = recipy.getTheRecette();
    if (jsonArray == null) {
        Toast.makeText(getApplicationContext(), "Error the array don't have value", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Menu_Recipy.class);
        startActivity(i);
    } else {

        try {

            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String content = jsonObject.getString("contenu");
            Ttitle.setText("the title of the recipy is : "+recipyName);
            Tcontent.setText(content);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}catch (Exception e){
    e.printStackTrace();
}
}



    @Override
    public void onClick(View v) {
        Intent intent = null;
        try {
            if (btnDelete.getId() == v.getId()) {
                if (recipy.deleteRecette()) {
                    Toast.makeText(getApplicationContext(), "The recipy is deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error The recipy can't be deleted", Toast.LENGTH_SHORT).show();
                }
                intent = new Intent(DisplayRecipy.this, Menu_Recipy.class);

            }
            String id = String.valueOf(MainActivity.USER.getId());
            intent.putExtra("id", id);
            intent.putExtra("nom", MainActivity.USER.getNom());
            intent.putExtra("logins", MainActivity.USER.getLogin());
            startActivity(intent);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
