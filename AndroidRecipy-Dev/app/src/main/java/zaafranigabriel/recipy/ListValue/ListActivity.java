package zaafranigabriel.recipy.ListValue;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import zaafranigabriel.recipy.Core.Recette;
import zaafranigabriel.recipy.Menu_Recipy;
import zaafranigabriel.recipy.R;

public class ListActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    Recette recette;
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
            ArrayAdapter<Recette> adapter = new ArrayAdapter<Recette>(this, android.R.layout.simple_list_item_1, liste);
            list.setAdapter(adapter);
            list.setOnItemClickListener(this);
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView tmpText = (TextView) view;
        if(recette.deleteRecette(Menu_Recipy.user.getId(),tmpText.getText().toString())){
            Toast.makeText(this,"The is element is deleted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"The is element is not deleted",Toast.LENGTH_SHORT).show();
        }
    }
}
