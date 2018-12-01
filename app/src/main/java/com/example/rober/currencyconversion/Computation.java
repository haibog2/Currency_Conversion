package com.example.rober.currencyconversion;

import android.util.Log;

import java.util.HashMap;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import java.lang.reflect.Type;
import org.json.JSONObject;
import com.google.gson.Gson;

/**
 * Where the actual calculation takes place.
 */
public class Computation {
    /** time of retrieval of the data.
     */
    private String time;
    /** the base case currency the user chose.
     */
    private static String basecase;
    /** a HashMap storing all the currency and their corresponding rate of exchange according to the
     * chosen base case.
     */
    private HashMap<String, Double> bcrate;
    /** a HashMap storing all the currency and their corresponding rate of exchange.
     */
    private HashMap<String, Double> rate;
    /** The type of rate stored as a type object.
     */
    private Type type = new TypeToken<HashMap<String, Object>>() { }.getType();

    /**initializing a instance of computation, with all data from the json object processed.
     * @param resp the json object to be processed.
     */
    public Computation(final JSONObject resp) {
        try {
            String timestamp = Integer.toString(resp.getInt("timestamp")).substring(0, 3);
            time =  timestamp.substring(0, 1) + " : " + timestamp.substring(2, 3);
            Gson a = new Gson();
            HashMap<String, Double> hm = a.fromJson("rates", type);
            rate.putAll(hm);
            setBaseCase("EUR");
        } catch (JSONException e) {
            Log.e("APP->src", "Computation: unexpected json error", e);
        }
    }

    /** retrieve the conversion rate of each currency.
     * @param abbrev currency abbreviation as in 3 letters.
     * @return the exchange rate.
     */
    public double getRate(final String abbrev) {
        return rate.get(abbrev);
    }

    public void setBaseCase(final String abbrev) {
        basecase = abbrev;
        if (abbrev == "EUR") {
            bcrate = rate;
        } else {
            for (rate.Entry<String, String> entry : rate.entrySet())
        }
    }
}
