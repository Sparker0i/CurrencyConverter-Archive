package com.a5corp.currencyconverter.data;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONRetriever {
    private static String link = "http://www.mycurrency.net/service/rates";

    public Currency[] getJSON() {
        try {
            Currency[] currency = null;

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder json = new StringBuilder(1024);
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String tmp;
            while((tmp = reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = json.toString();
            try{
                currency = mapper.readValue(jsonString, Currency[].class);

                //System.out.println(currency);

                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                jsonString = mapper.writeValueAsString(currency);

                System.out.println(jsonString);
            }
            catch (JsonParseException e) { e.printStackTrace();}
            catch (JsonMappingException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
            return currency;
        }
        catch (MalformedURLException mue) {

        }
        catch (IOException ioe) {

        }
        return null;
    }
}