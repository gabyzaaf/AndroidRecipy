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
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

import zaafranigabriel.recipydesign.Adapter.GoogleListAdapter;
import zaafranigabriel.recipydesign.App.MapsActivity;
import zaafranigabriel.recipydesign.Class.Core.Recipy;
import zaafranigabriel.recipydesign.Class.Core.User;
import zaafranigabriel.recipydesign.Class.RowListView.GoogleSingleRow;
import zaafranigabriel.recipydesign.Exception.SendException;
import zaafranigabriel.recipydesign.R;

/**
 * Created by zaafranigabriel on 18/06/2016.
 */
public class FragmentGoogle extends Fragment implements AdapterView.OnItemClickListener{
    private String logins;
    private int id;
    private User user;
    public FragmentGoogle(){
        Log.i("fragment Check", "fragment on created");
    }
    ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logins = getArguments().getString("logins");
        int id = Integer.parseInt(getArguments().getString("id"));
        user = new User(id,logins);
        return inflater.inflate(R.layout.fragmentgoogle,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listView= (ListView) view.findViewById(R.id.googleListView);
        Recipy recipy = new Recipy();
        try {
            JSONArray object = recipy.getRecette(user);
            if (object.length() == 0) {
                Toast.makeText(view.getContext(), "Your user don't have value ", Toast.LENGTH_SHORT).show();
            } else {
                LinkedList<Recipy> liste = new LinkedList<>();
                Recipy recette1;
              Loop: for (int i = 0; i < object.length(); i++) {
                    try {
                        JSONObject jsonobject = object.getJSONObject(i);
                        int id = Integer.parseInt(jsonobject.getString("id"));
                        String content = jsonobject.getString("adresse");
                        if(content.equals("null")){
                            int v = 2;
                            v = v+1;
                            continue Loop;
                        }
                        String title = jsonobject.getString("title");

                        recette1 = new Recipy(id, title, content);
                        recette1.setImg(R.drawable.googleiconsmall);
                        liste.add(recette1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listView.setAdapter(new GoogleListAdapter(getContext(), liste));
                listView.setOnItemClickListener(this);
            }
        }catch (SendException e){
            e.getMessage();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoogleSingleRow clickedObj = (GoogleSingleRow)parent.getItemAtPosition(position);


        try {
            Recipy recipy = new Recipy();
            recipy.setTitle(clickedObj.getTitle());
            recipy.setId(user.getId());
            JSONArray array = recipy.getLattitudeAndLongitude();
            JSONObject object= array.getJSONObject(0);

            Intent intent = new Intent(getContext(), MapsActivity.class);
            intent.putExtra("Latitude", object.getString("longitude"));
            intent.putExtra("Longitude", object.getString("lattitude"));
            startActivity(intent);
        } catch (SendException e) {
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
