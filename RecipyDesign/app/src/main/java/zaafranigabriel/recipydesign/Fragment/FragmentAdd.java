package zaafranigabriel.recipydesign.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import zaafranigabriel.recipydesign.Class.Core.Recipy;
import zaafranigabriel.recipydesign.Class.Core.User;
import zaafranigabriel.recipydesign.Exception.SendException;
import zaafranigabriel.recipydesign.R;

/**
 * Created by zaafranigabriel on 18/06/2016.
 */
public class FragmentAdd extends Fragment implements View.OnClickListener {

    private String logins;
    private String id;
    private User user;
    private Button btn = null;

    private EditText title,content,adress;



    public FragmentAdd(){
        Log.i("fragment Check", "fragment on created");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         logins = getArguments().getString("logins");
        int id = Integer.parseInt(getArguments().getString("id"));
         user = new User(id,logins);

        return inflater.inflate(R.layout.fragmentadd,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btn = (Button)view.findViewById(R.id.btn_add);
        title = (EditText)view.findViewById(R.id.recipeName);
        content = (EditText)view.findViewById(R.id.contentRecipe);
        adress = (EditText)view.findViewById(R.id.adress);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==btn.getId()){

            if(title.getText().toString().equals("") || content.getText().toString().equals("") || adress.getText().toString().equals("")){
                Toast.makeText(v.getContext(),"The form must be complet",Toast.LENGTH_SHORT).show();
            }else{
                Recipy recipy = new Recipy(title.getText().toString(),content.getText().toString(),adress.getText().toString());
                try {
                    if(recipy.add(user)){
                        Toast.makeText(v.getContext(),"The recipe are added",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(v.getContext(),"The recipe can't be added",Toast.LENGTH_SHORT).show();
                    }
                } catch (SendException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
