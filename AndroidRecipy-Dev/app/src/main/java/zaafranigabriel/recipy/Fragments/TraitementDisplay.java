package zaafranigabriel.recipy.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zaafranigabriel.recipy.R;

/**
 * Created by zaafranigabriel on 21/05/2016.
 */
public class TraitementDisplay extends Fragment {

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_response_layout,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        try {
            super.onActivityCreated(savedInstanceState);
            textView = (TextView)getActivity().findViewById(R.id.responseText);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void changeText(String title,String content,String result){
        try {
            textView.setText("the Datas are added");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
