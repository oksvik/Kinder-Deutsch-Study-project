package com.example.dudar.mydeutsch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);
        findViewById(R.id.numbers).setOnClickListener(this);
        findViewById(R.id.colors).setOnClickListener(this);
        findViewById(R.id.family).setOnClickListener(this);
        findViewById(R.id.phrases).setOnClickListener(this);
    }

    // Implement the OnClickListener callback
    public void onClick (View v){
        Intent i = new Intent();
        String s = "";
        switch (v.getId()) {
            case R.id.numbers:
                s = "Open Numbers list";
                i = new Intent(this,NumbersActivity.class);
                break;
            case R.id.colors:
                s= "Open Colors list";
                //Toast.makeText(this, "Open Colors list", Toast.LENGTH_SHORT).show();
                i = new Intent(this,ColorsActivity.class);
                break;
            case R.id.family:
                s = "Open Family members list";
                //Toast.makeText(this, "Open Family members list", Toast.LENGTH_SHORT).show();
                i = new Intent(this,FamilyActivity.class);
                break;
            case R.id.phrases:
                s = "Open Phrases list";
                //Toast.makeText(this, "Open Phrases list", Toast.LENGTH_SHORT).show();
                i = new Intent(this,PhrasesActivity.class);
        }
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        startActivity(i);
        }


}
