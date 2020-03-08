package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    private String calcul;
    private TextView calculView;
    private TextView resultView;
    private Socket sock;
    private String calculAndResult;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.calcul = "";
        this.calculAndResult = "";
        this.calculView = findViewById(R.id.calculView);
        this.resultView = findViewById(R.id.resultView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu1:
                Intent intent = new Intent(this, ActivityTwo.class);
                intent.putExtra("calculAndResult", calculAndResult);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void clickHandler(View view){

        if(view.getId() == R.id.buttonEquals){

            //method1();
            //method2();
            method3();


        }else{
            this.resultView.setText("");
            this.calcul += ((Button)view).getText().toString();
            this.calculView.setText(this.calcul);
        }

    }

    public void method1(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if(!calcul.contains("+") && !calcul.contains("-") && !calcul.contains("/") && !calcul.contains("*")){
                    resultView.setText(calcul);
                    calcul = "";
                }

                if(calcul.contains("+")){

                    if(calcul.matches("[0-9]+(\\+[0-9]+)+")){
                        String operands[] =  calcul.split("\\+");

                        int result = 0;

                        for(String operand : operands){
                            result += Integer.parseInt(operand);
                        }

                        resultView.setText("" + result);

                    }
                    else{
                        resultView.setText("Err");
                    }

                    calcul = "";
                }

                calculView.setText(calcul);
            }
        };

        new Thread(runnable).start();


    }

    public void method2(){

        EqualAsyncTask asyncTask=new EqualAsyncTask();

        asyncTask.execute(calcul, calculView, resultView);
    }

    public void method3(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try{

                    sock = new Socket();
                    sock.connect(new InetSocketAddress("10.0.2.2", 9876));

                    DataInputStream dis = new DataInputStream(sock.getInputStream());
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());


                    if(calcul.contains("+")){

                        if(calcul.matches("[0-9]+(\\+[0-9]+)+")){
                            String operands[] =  calcul.split("\\+");

                            for(int i = 0; i<operands.length; i++){
                                dos.writeDouble(Double.parseDouble(operands[i]));

                                if(i<operands.length - 1){
                                    dos.writeChars("+");
                                }

                            }

                            double result = 0;

                            result = dis.readDouble(); // will only do a + b even if op is a+b+c because given server does not handle multi operations

                            resultView.setText("" + result);
                            calculAndResult = calcul + " : " + result;

                        }
                        else{
                            resultView.setText("Err");
                            calculAndResult = calcul + " : Err";
                        }

                        calcul = "";
                    }

                }catch (Exception e){
                    System.out.println("err");
                    System.out.println(e);
                    System.out.println(e.getMessage());
                }
            }
        };

        new Thread(runnable).start();

    }
}
