package com.mobileappclub.android.internetthings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * An activity for displaying the weather.
 */
public class WeatherActivity extends Activity {

    private static final String API_KEY = "8b00b7cbb267714293910909a4b56ded";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        RequestQueue queue = Volley.newRequestQueue(this);

        String city = getIntent().getStringExtra("city");
        String requestUrl = BASE_URL + "q=" + city + "&units=imperial&APPID=" + API_KEY;

        // Bind program elements.
        final TextView summary = (TextView) findViewById(R.id.tvSummary);
        final TextView description = (TextView) findViewById(R.id.tvDescription);
        final TextView tempRange = (TextView) findViewById(R.id.tvTempRange);

        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
                    summary.setText(weather.getString("main"));
                    description.setText(weather.getString("description"));

                    // Retrieve the temperature info
                    JSONObject main = response.getJSONObject("main");
                    tempRange.setText(main.getString("temp_min") + " - " + main.getString("temp_max"));

                    // Reaching this point means it's safe to replace the spinner
                    ProgressBar spinner = (ProgressBar) findViewById(R.id.pbSpinner);
                    RelativeLayout contentView = (RelativeLayout) findViewById(R.id.rlContentView);
                    spinner.setVisibility(View.GONE);
                    contentView.setVisibility(View.VISIBLE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // We would normally handle errors here.
            }
        });

        queue.add(jRequest);
    }
}
