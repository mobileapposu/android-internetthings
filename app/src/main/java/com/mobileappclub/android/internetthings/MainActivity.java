package com.mobileappclub.android.internetthings;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLayout();
    }

    private void setupLayout() {
        final EditText cityTextBox = (EditText) findViewById(R.id.etCity);
        Button goButton = (Button) findViewById(R.id.bGo);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityTextBox.getText().toString();

                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("city", city);
                startActivity(intent);
            }
        });
    }
}
