package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String calcul;
    private TextView calculView;
    private TextView resultView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.calcul = "";
        this.calculView = findViewById(R.id.calculView);
        this.resultView = findViewById(R.id.resultView);

    }

    public void clickHandler(View view){

        if(view.getId() == R.id.buttonEquals){

            if(!this.calcul.contains("+") && !this.calcul.contains("-") && !this.calcul.contains("/") && !this.calcul.contains("*")){
                this.resultView.setText(this.calcul);
                this.calcul = "";
            }

            if(this.calcul.contains("+")){

                if(this.calcul.matches("[0-9]+(\\+[0-9]+)+")){
                    String operands[] =  this.calcul.split("\\+");

                    int result = 0;

                    for(String operand : operands){
                        result += Integer.parseInt(operand);
                    }

                    this.resultView.setText("" + result);

                }
                else{
                    this.resultView.setText("Err");
                }

                this.calcul = "";
            }


        }else{
            this.resultView.setText("");
            this.calcul += ((Button)view).getText().toString();
        }

        this.calculView.setText(this.calcul);
        view.invalidate();


    }
}
