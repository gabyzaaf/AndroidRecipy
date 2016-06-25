package zaafranigabriel.recipy.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import zaafranigabriel.recipy.Communicator;
import zaafranigabriel.recipy.R;

/**
 * Created by zaafranigabriel on 21/05/2016.
 */
public class AddCook extends Fragment implements View.OnClickListener{

    Button btn;
    EditText title;
    EditText content;
    EditText adress;
    Communicator communicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            return inflater.inflate(R.layout.form_display, container, false);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
       try {
           super.onActivityCreated(savedInstanceState);
           communicator = (Communicator) getActivity();
           title = (EditText) getActivity().findViewById(R.id.titleElement);
           content = (EditText) getActivity().findViewById(R.id.contentElement);
           adress = (EditText) getActivity().findViewById(R.id.adressElement);
           btn = (Button) getActivity().findViewById(R.id.buttonSave);
           btn.setOnClickListener(this);
       }catch(Exception e){
            e.printStackTrace();
       }
    }

    @Override
    public void onClick(View v) {
        try {
            String stitle = title.getText().toString();
            String scontent = content.getText().toString();
            String sadress = adress.getText().toString();
            communicator.result(stitle, scontent, sadress);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
