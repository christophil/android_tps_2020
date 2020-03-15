package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu1:
                Intent intent = new Intent(this, ActivityTwo.class);
                intent.putExtra("calculAndResult", "");
                this.startActivity(intent);
                break;
            case R.id.menu2:
                Intent intent2 = new Intent(this, MainActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.menu3:
                Intent intent3 = new Intent(this, EditActivity.class);
                this.startActivity(intent3);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onBackPressed() {
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
