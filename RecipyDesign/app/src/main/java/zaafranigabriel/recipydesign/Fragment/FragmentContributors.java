package zaafranigabriel.recipydesign.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zaafranigabriel.recipydesign.Class.Core.User;
import zaafranigabriel.recipydesign.R;

/**
 * Created by zaafranigabriel on 24/06/2016.
 */
public class FragmentContributors extends Fragment {

    private String logins;
    private int id;
    private User user;
    public FragmentContributors(){
        Log.i("fragment Check", "fragment on created");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logins = getArguments().getString("logins");
        int id = Integer.parseInt(getArguments().getString("id"));
        user = new User(id,logins);
        return inflater.inflate(R.layout.fragmentcontributors,container,false);
    }



}
