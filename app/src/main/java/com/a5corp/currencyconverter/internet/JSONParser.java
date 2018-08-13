package com.a5corp.currencyconverter.internet;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParser extends AsyncTask<Void , Void , JSONObject> {

    private Context context;

    public JSONParser(Context context) {
        this.context = context;
    }


    @Override
    protected JSONObject doInBackground(Void... voids) {
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open("countries.json");
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();

                builder.append(word);
            }
            return new JSONObject(builder.toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
