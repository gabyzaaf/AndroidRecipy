package clapert.recipyandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAfficheMap;
    EditText etlatitude;
    EditText etlongetude;
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAfficheMap = (Button)findViewById(R.id.btnAfficheMap);
        btnAfficheMap.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        //btnAfficheMap.setText(latitude.getText());
        etlatitude = (EditText)findViewById(R.id.etLatitude);
        etlongetude = (EditText)findViewById(R.id.etLongetude);
        latitude = etlatitude.getText().toString();
        longitude = etlongetude.getText().toString();
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("Latitude", latitude);
        intent.putExtra("Longitude", longitude);
        startActivity(intent);
    }
}
