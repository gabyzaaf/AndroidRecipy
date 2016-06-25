package zaafranigabriel.recipy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import zaafranigabriel.recipy.Core.User;
import zaafranigabriel.recipy.WebService.WsGeneral;

public class MainActivity extends Activity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.submit);
        final EditText login = (EditText)findViewById(R.id.EditLogin);
        final EditText password = (EditText)findViewById(R.id.passwordText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login.getText().equals("") || login.getText()==null || password.getText().equals("") || password.getText()==null)
                {
                    Toast.makeText(getApplicationContext(), "Error you credentials are wrong", Toast.LENGTH_LONG).show();

                }else
                {
                    HashMap<String,String> listValue = new HashMap<String, String>();
                    listValue.put("url","http://192.168.0.35/recipy/WSconnect.php");
                    listValue.put("login",login.getText().toString());
                    listValue.put("pass", password.getText().toString());
                    try {
                        String value = new WsGeneral().execute(listValue).get();
                        Utilities utilities = new Utilities(value);
                        JSONObject jsonObject = utilities.getJson();
                        User user = new User(Integer.parseInt(jsonObject.get("id").toString()),jsonObject.get("nom").toString(),jsonObject.get("logins").toString());
                        Intent intent = new Intent(MainActivity.this,Menu_Recipy.class);

                        intent.putExtra("id",jsonObject.get("id").toString());
                        intent.putExtra("nom",jsonObject.get("nom").toString());
                        intent.putExtra("logins",jsonObject.get("logins").toString());
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }catch(JSONException e){
                        e.printStackTrace();

                    }
                }
            }
        });

    }


}
