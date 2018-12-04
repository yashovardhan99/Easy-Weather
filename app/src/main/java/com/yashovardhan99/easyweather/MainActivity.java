package com.yashovardhan99.easyweather;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RequestQueue queue;
    private JsonObjectRequest request;
    TextView tempTextView, conditionTextView, minMaxTextView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempTextView = findViewById(R.id.temp);
        conditionTextView = findViewById(R.id.condition);
//        minMaxTextView = findViewById(R.id.min_max_temp);
        button = findViewById(R.id.fetch);

        queue = Volley.newRequestQueue(this);

        final String api_key = getString(R.string.open_weather_api_key);
        String location = "Jaipur";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=" + api_key;

        request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RESPONSE", response.toString());
                        try {
                            String temp = response.getJSONObject("main").getString("temp");
                            String icon_id = response.getJSONArray("weather").getJSONObject(0).getString("icon");
                            String condition = response.getJSONArray("weather").getJSONObject(0).getString("description");
//                            String min_temp = response.getJSONObject("main").getString("temp_min");
//                            String max_temp  =response.getJSONObject("main").getString("temp_max");
                            int temp_c = (int) (Double.parseDouble(temp) - 273.15);
//                            int min_temp_c = (int) (Double.parseDouble(min_temp) - 273.15);
//                            int max_temp_c = (int) (Double.parseDouble(max_temp) - 273.15);
                            String display_temp = getString(R.string.celcius_symbol, temp_c);
//                            String display_temp_minMax = getString(R.string.celcius_symbol, min_temp_c) +" / " + getString(R.string.celcius_symbol, max_temp_c);
                            tempTextView.setText(display_temp);
//                            minMaxTextView.setText(display_temp_minMax);
                            conditionTextView.setText(condition);
                            Picasso.get().load(getString(R.string.icon_url,icon_id)).fit().into((ImageView) findViewById(R.id.icon));
                        } catch (JSONException e) {
                            Log.e("JSON", e.toString());
                        } catch (NumberFormatException e){
                            Log.e("NumberFormat",e.toString());
                        }
                        finally {
                            Runnable buttonUpdater = new Runnable() {
                                @Override
                                public void run() {
                                    button.setEnabled(true);
                                }
                            };
                            button.postDelayed(buttonUpdater, 10000);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tempTextView.setText("An error occurred");
                        error.printStackTrace();
                        button.setEnabled(true);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Log.d("MAINACTIVITY", "OnClick");
        queue.add(request);
        button.setEnabled(false);
        tempTextView.setError(null);
    }
}
