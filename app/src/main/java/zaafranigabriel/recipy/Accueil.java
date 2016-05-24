package zaafranigabriel.recipy;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import zaafranigabriel.recipy.Fragments.TraitementDisplay;
import zaafranigabriel.recipy.WebService.WsGeneral;

public class Accueil extends Activity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }


    @Override
    public void result(String title, String content, String result) {
        try {
            Intent intent = getIntent();

            FragmentManager fragmentManager = getFragmentManager();
            TraitementDisplay traitementDisplay = (TraitementDisplay) fragmentManager.findFragmentById(R.id.fragment2);
            HashMap<String, String> hashValue = new HashMap<>();
            hashValue.put("url", "http://10.211.55.4/database/WSaddRecette.php");
            hashValue.put("id", String.valueOf(Menu_Recipy.user.getId()));
            hashValue.put("title", title);
            hashValue.put("content", content);
            hashValue.put("adress", result);
            String value = null;
            value = new WsGeneral().execute(hashValue).get();
            JSONObject json = WsGeneral.getObject(value);
            traitementDisplay.changeText(title, content, result);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }

    }



}
