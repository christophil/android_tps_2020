package com.example.tp2;

import android.os.AsyncTask;
import android.widget.TextView;

public class EqualAsyncTask extends AsyncTask<Object, Void, Reference> {

    private String calcultoSet;
    private String resultToSet;

    @Override
    protected Reference doInBackground(Object[] params) {

        String calcul = params[0].toString();
        TextView calculView = (TextView) params[1];
        TextView resultView = (TextView) params[2];

        Reference ref = new Reference(calculView, resultView);

        this. calcultoSet = "";

        if(!calcul.contains("+") && !calcul.contains("-") && !calcul.contains("/") && !calcul.contains("*")){
            this.calcultoSet = calcul;
        }

        if(calcul.contains("+")){

            if(calcul.matches("[0-9]+(\\+[0-9]+)+")){
                String operands[] =  calcul.split("\\+");

                int res = 0;

                for(String operand : operands){
                    res += Integer.parseInt(operand);
                }

                this.resultToSet = "" + res;

            }
            else{
                this.resultToSet = "Err";
            }

            this.calcultoSet = "";
        }

        return ref;
    }

    @Override
    protected void onPostExecute(Reference ref) {
        super.onPostExecute(ref);

        ref.calculView.setText(this.calcultoSet);
        ref.resultView.setText(this.resultToSet);

    }

}
