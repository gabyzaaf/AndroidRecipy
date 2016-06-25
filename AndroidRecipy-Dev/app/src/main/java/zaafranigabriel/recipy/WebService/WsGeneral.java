package zaafranigabriel.recipy.WebService;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zaafranigabriel on 09/05/2016.
 */

public class WsGeneral extends AsyncTask<HashMap<String,String>,Void,String> {

    StringBuffer buf = new StringBuffer();

    @Override
    protected String doInBackground(HashMap<String, String>... params){
        String line;
        BufferedReader in=null;
        URL url = null;
        try {
            String value = params[0].get("url");
            url = new URL(value);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        try {
            conn.setRequestMethod("POST");

        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setDoInput(true);
        conn.setDoOutput(true);
        OutputStream os = null;
        try {
            os = conn.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = null;
        try {
            //Log.d("RESULTERR",buf.toString());
            writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            writer.write(getQuery(params[0]));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            in= new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line=in.readLine())!=null)
            {
                buf.append(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        conn.disconnect();

        Log.d("RESULTERR",buf.toString());

        return buf.toString();
    }

    private String getQuery(HashMap<String,String> liste) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator it = liste.entrySet().iterator();
        while (it.hasNext())
        {

            Map.Entry pair = (Map.Entry)it.next();
            if(first)
            first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(String.valueOf(pair.getKey()), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(String.valueOf(pair.getValue()), "UTF-8"));

        }
        return result.toString();
    }

    public static  JSONObject getObject(String jsonValue){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(jsonValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static JSONArray getJsonArray(String jsonValue){
        JSONArray data = null;
        try {
            data = new JSONArray(jsonValue);
        }catch (Exception e){
            e.printStackTrace();
        }
    return data;
    }

}
