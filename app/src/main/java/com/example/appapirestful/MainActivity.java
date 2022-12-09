package com.example.appapirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        String datos= "";
        ArrayList<String> lstBancos = new ArrayList<String> ();
        JSONObject objectjson = new JSONObject(result);
        JSONArray JSONlista = objectjson.getJSONArray("data");

        for(int i=0; i< JSONlista.length();i++){
            JSONObject banco=  JSONlista.getJSONObject(i);
            datos= datos + ("ID: "+ banco.getString("id") + "name: " + banco.getString("name") + "\n" +
                    "email: "+ banco.getString("email") + "\n" +
                    "gender: "+ banco.getString("gender") + "\n" +
                    "status: "+ banco.getString("status") +"\n\n\n\n");
        }

        TextView txtver = (TextView) findViewById(R.id.txtlista);
        txtver.setText(datos);
    }

    public void btnenviarwebservice (View view){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                datos, MainActivity.this, MainActivity.this);
        AsyncTask<String, Long, String> get = ws.execute("GET");
    }
}