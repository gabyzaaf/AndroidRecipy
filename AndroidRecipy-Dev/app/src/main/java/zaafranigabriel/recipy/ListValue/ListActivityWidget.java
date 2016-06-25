package zaafranigabriel.recipy.ListValue;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

import zaafranigabriel.recipy.Core.Recette;
import zaafranigabriel.recipy.Menu_Recipy;
import zaafranigabriel.recipy.R;
import zaafranigabriel.recipy.Widget.WidgetList;

/**
 * Created by HP 750-240nf on 23/06/2016.
 */
public class ListActivityWidget extends Activity{

    Recette recette;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        try {
            list = (ListView) findViewById(R.id.listView);
            recette = new Recette();
            JSONArray object = recette.getRecette(Menu_Recipy.user.getId());
            LinkedList<Recette> liste = new LinkedList<>();
            Recette recette1;
            for (int i = 0; i < object.length(); i++) {
                JSONObject jsonobject = object.getJSONObject(i);
                int id = Integer.parseInt(jsonobject.getString("id"));
                String title = jsonobject.getString("title");
                String content = jsonobject.getString("contenu");
                recette1 = new Recette(id,title,content);
                liste.add(recette1);
            }
            ArrayAdapter<Recette> adapter = new ArrayAdapter<Recette>(this, android.R.layout.simple_list_item_1,liste);
            list.setAdapter(adapter);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

}
