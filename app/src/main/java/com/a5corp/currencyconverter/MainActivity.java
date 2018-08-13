package com.a5corp.currencyconverter;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.a5corp.currencyconverter.internet.JSONParser;
import com.a5corp.currencyconverter.model.CurrencyDatabase;
import com.a5corp.currencyconverter.model.CurrencyTable;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "db_currency";
    private CurrencyDatabase currencyDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ArrayList<CurrencyTable> currencies = new ArrayList<>();

        currencyDatabase = Room.databaseBuilder(this , CurrencyDatabase.class , DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(this, (String) value, Toast.LENGTH_LONG).show();
            } catch (Exception ignore) {

            }
        }

        try {
            JSONObject results = new JSONParser(this).execute().get().getJSONObject("results");
            Iterator<String> keys = results.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject innerobject = results.getJSONObject(key);
                Log.v("Category Key" , key);

                String cname = innerobject.getString("currencyName");
                String id = innerobject.getString("id");

                CurrencyTable c = new CurrencyTable();
                c.setCurrencyName(cname);
                c.setId(id);
                currencies.add(c);
                Log.v("key = " + key , cname + " " + id);
            }
            System.out.println(results);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    currencyDatabase.daoAccess().insertMultipleCurrencies(currencies);
                }
            }).start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
