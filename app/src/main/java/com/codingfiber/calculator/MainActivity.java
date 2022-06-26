package com.codingfiber.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView solutiontv,resulttv;
    MaterialButton buttonc,button_openbracket,button_closebracket,button_div,button_7,button_8,button_9,bitton_ast,button_4,button_5,button_6,button_min;
MaterialButton button_1,button2,button_3,button_plus,button_zero,button_dot,button_eq,buttonac;
//intiliaze all button and textview here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutiontv = findViewById(R.id.solutiontv);
        resulttv = findViewById(R.id.resulttv);

// give the refernce all button and texview and implement onclicklistener for button
        assignId(buttonc,R.id.buttonc);
        assignId(button_openbracket,R.id.button_openbracket);
        assignId(button_closebracket,R.id.button_closebracket);
        assignId(button_div,R.id.button_div);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_min,R.id.button_min);
        assignId(button_1,R.id.button_1);
        assignId(button2,R.id.button2);
        assignId(button_3,R.id.button_3);
        assignId(button_plus,R.id.button_plus);
        assignId(button_zero,R.id.button_zero);
        assignId(button_dot,R.id.button_dot);
        assignId(button_eq,R.id.button_eq);
        assignId(bitton_ast,R.id.bitton_ast);
        assignId(buttonac,R.id.buttonac);
    }
void assignId(MaterialButton button,int id){
        button = findViewById(id);
        button.setOnClickListener(this);
}
    @Override
    public void onClick(View view) {
MaterialButton button = (MaterialButton)view;
String buttontext = button.getText().toString();
String Datacalculate = solutiontv.getText().toString();

if (buttontext.equals("AC")){
    solutiontv.setText("");
   resulttv.setText("0");
   return;
}
if (buttontext.equals("=")){
    solutiontv.setText(resulttv.getText());
    return;
}
if (buttontext.equals("C")){
Datacalculate = Datacalculate.substring(0,Datacalculate.length()-1);
}
else {
    Datacalculate = Datacalculate+buttontext;
    String finalResult = getResult(Datacalculate);
    if (!finalResult.equals("err")){
        resulttv.setText(finalResult);
    }
}

solutiontv.setText(Datacalculate);
    }
    String getResult (String data){

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "err";
        }
    }
}