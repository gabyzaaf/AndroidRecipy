package zaafranigabriel.recipydesign.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import zaafranigabriel.recipydesign.Adapter.GoogleListAdapter;
import zaafranigabriel.recipydesign.App.DisplayRecipy;
import zaafranigabriel.recipydesign.Class.Core.Recipy;
import zaafranigabriel.recipydesign.Class.Core.User;
import zaafranigabriel.recipydesign.Class.RowListView.GoogleSingleRow;
import zaafranigabriel.recipydesign.Exception.SendException;
import zaafranigabriel.recipydesign.R;

/**
 * Created by zaafranigabriel on 18/06/2016.
 */
public class FragmentDelete extends Fragment implements AdapterView.OnItemClickListener{
    private String logins;
    private int id;
    private User user;
    public FragmentDelete(){
        Log.i("fragment Check", "fragment on created");
    }
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logins = getArguments().getString("logins");
        int id = Integer.parseInt(getArguments().getString("id"));
        user = new User(id,logins);
        return inflater.inflate(R.layout.fragmentdelete,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.listView);
        Recipy recipy = new Recipy();
        JSONArray object = null;
        try {
            object = recipy.getRecette(user);


            if (object.length() == 0) {
                Toast.makeText(view.getContext(), "Your user don't have value ", Toast.LENGTH_SHORT).show();
            } else {
                LinkedList<Recipy> liste = new LinkedList<>();
                Recipy recette1;
                for (int i = 0; i < object.length(); i++) {
                    try {
                        JSONObject jsonobject = object.getJSONObject(i);
                        int id = Integer.parseInt(jsonobject.getString("id"));
                        String title = jsonobject.getString("title");
                        String content = jsonobject.getString("contenu");
                        recette1 = new Recipy(id, title, content);
                        recette1.setImg(R.drawable.cakeimg);
                        liste.add(recette1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listView.setAdapter(new GoogleListAdapter(getContext(), liste));
                listView.setOnItemClickListener(this);
            }
        }catch (SendException e ){
            e.printStackTrace();
        }
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        GoogleSingleRow clickedObj = (GoogleSingleRow)parent.getItemAtPosition(position);
        try {


            Intent intent = new Intent(view.getContext(), DisplayRecipy.class);
            intent.putExtra("recipyName", clickedObj.getTitle());
            intent.putExtra("recipyId", user.getId());
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
