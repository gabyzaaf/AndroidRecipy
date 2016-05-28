package zaafranigabriel.recipy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import zaafranigabriel.recipy.Core.User;
import zaafranigabriel.recipy.ListValue.ListActivity;

public class Menu_Recipy extends Activity implements View.OnClickListener {

    Button btnAddCook;
    Button btnDeleteCook;
    Button btnDisplayMap;
    public static User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        user = new User( Integer.parseInt(intent.getStringExtra("id")),intent.getStringExtra("nom"),intent.getStringExtra("Logins"));
        setContentView(R.layout.activity_menu__recipy);
        btnAddCook = (Button)findViewById(R.id.AddCook);
        btnAddCook.setOnClickListener(this);
        btnDeleteCook = (Button)findViewById(R.id.DeleteCook);
        btnDeleteCook.setOnClickListener(this);
        btnDisplayMap = (Button)findViewById(R.id.DisplayMap);
        btnDisplayMap.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==btnDisplayMap.getId()){
            Intent intent = new Intent(Menu_Recipy.this,DisplayMap.class);
            startActivity(intent);
        }else if(v.getId()==btnAddCook.getId()){
            Intent intent = new Intent(Menu_Recipy.this,Accueil.class);
            startActivity(intent);
        }else if(v.getId()==btnDeleteCook.getId()){
            Intent intent = new Intent(Menu_Recipy.this, ListActivity.class);
            startActivity(intent);
        }

    }
}
