package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    private EditText urlView;
    private TextView calculAndResultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        this.urlView = findViewById(R.id.urlView);
        this.calculAndResultView = findViewById(R.id.calculAndResultView);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            this.calculAndResultView.setText(extras.getString("calculAndResult"));
        }

    }

    public void clickHandler(View view){

        String url = this.urlView.getText().toString();

        if (!url.startsWith("https://") && !url.startsWith("http://")){
            url = "http://" + url;
        }

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
