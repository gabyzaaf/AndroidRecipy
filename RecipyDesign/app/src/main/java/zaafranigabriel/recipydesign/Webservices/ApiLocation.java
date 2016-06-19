package zaafranigabriel.recipydesign.Webservices;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zaafranigabriel on 31/05/2016.
 */
public class ApiLocation extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {

        StringBuilder stringBuilder = null;
        try {
        if(params[0].contains(" "))
        {
            params[0]= params[0].replace(" ","+");

        }
        String url = "http://maps.googleapis.com/maps/api/geocode/json?address="+params[0]+"&sensor=true";
        String jsonString = null;
        if(params.length==0 || params==null)
        {
            return null;
        }

            URL url1 = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url1.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            stringBuilder= new StringBuilder();
            while ((jsonString=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(jsonString);

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        }catch(MalformedURLException e)
        {
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return stringBuilder.toString();
        }
    }
}
