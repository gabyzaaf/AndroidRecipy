package zaafranigabriel.recipydesign.App;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import zaafranigabriel.recipydesign.Adapter.IconAdapter;
import zaafranigabriel.recipydesign.Fragment.FragmentAdd;
import zaafranigabriel.recipydesign.Fragment.FragmentContributors;
import zaafranigabriel.recipydesign.Fragment.FragmentDelete;
import zaafranigabriel.recipydesign.Fragment.FragmentGoogle;
import zaafranigabriel.recipydesign.R;

/**
 * Created by zaafranigabriel on 18/06/2016.
 */
public class Menu_Recipy  extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_recipy);
        initialise();
        prepareDataSource();
        IconAdapter adapter = new IconAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcon();
    }

    private void initialise() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Recipy menu");
        viewPager= (ViewPager)findViewById(R.id.viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabsLayout);

    }

    private void prepareDataSource(){

        Intent intent = getIntent();

        String id  = intent.getStringExtra("id");
        String nom = intent.getStringExtra("nom");
        String logins = intent.getStringExtra("logins");

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("nom", nom);
        bundle.putString("logins", logins);


        fragmentList.add(new FragmentAdd());
        fragmentList.add(new FragmentContributors());
        fragmentList.add(new FragmentDelete());
        fragmentList.add(new FragmentGoogle());


        for(int i = 0;i<fragmentList.size();i++){
            fragmentList.get(i).setArguments(bundle);
        }
    }
    private void setTabIcon() {
        tabLayout.getTabAt(0).setIcon(R.drawable.addicon);
        tabLayout.getTabAt(1).setIcon(R.drawable.recipeicon);
        tabLayout.getTabAt(2).setIcon(R.drawable.deleteicon);
        tabLayout.getTabAt(3).setIcon(R.drawable.icongoogle);

    }
}
