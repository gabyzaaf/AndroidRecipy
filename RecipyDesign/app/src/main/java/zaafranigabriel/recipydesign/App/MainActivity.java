package zaafranigabriel.recipydesign.App;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import zaafranigabriel.recipydesign.Exception.SendException;
import zaafranigabriel.recipydesign.R;
import zaafranigabriel.recipydesign.Class.Core.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText tlogin,tpassword;
    Button btn;
    JSONObject wsObjet = null;
    public static User USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       tlogin = (EditText) findViewById(R.id.login);
       tpassword = (EditText) findViewById(R.id.pass);
       btn = (Button)findViewById(R.id.btn_connect);
       btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == btn.getId()) {

            User user = new User(tlogin.getText().toString(),tpassword.getText().toString());
            wsObjet = user.getConnexion();
            if (wsObjet == null) {
                Toast.makeText(getApplicationContext(), "Is not the good login and password", Toast.LENGTH_SHORT).show();

            }else{
            try {
                    USER = new User(Integer.parseInt(wsObjet.get("id").toString()),wsObjet.get("logins").toString());
                    USER.setNom(wsObjet.get("nom").toString());
                    Intent intent = new Intent(MainActivity.this, Menu_Recipy.class);
                    intent.putExtra("id", wsObjet.get("id").toString());
                    intent.putExtra("nom", wsObjet.get("nom").toString());
                    intent.putExtra("logins", wsObjet.get("logins").toString());
                    startActivity(intent);
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }
    }
}
