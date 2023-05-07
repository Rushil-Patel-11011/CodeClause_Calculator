package com.example.codeclause_simple_calculator_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,solution;
    MaterialButton button_c,open_bracket,close_bracket,button_ac,button_7,button_8,button_9,button_6,button_5,button_4,button_3,button_2,button_1,button_0,addition,subtraction,division,multiplication,equal,button_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(button_c,R.id.button_c);
        assignId(open_bracket,R.id.open_bracket);
        assignId(close_bracket,R.id.close_bracket);
        assignId(button_ac,R.id.button_ac);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_6,R.id.button_6);
        assignId(button_5,R.id.button_5);
        assignId(button_4,R.id.button_4);
        assignId(button_3,R.id.button_3);
        assignId(button_2,R.id.button_2);
        assignId(button_1,R.id.button_1);
        assignId(button_0,R.id.button_0);
        assignId(addition,R.id.addition);
        assignId(subtraction,R.id.subtraction);
        assignId(division,R.id.division);
        assignId(multiplication,R.id.multiplication);
        assignId(equal,R.id.equal);
        assignId(button_dot,R.id.button_dot);

    }

    void assignId(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button  = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if (buttonText.equals("AC"))
        {
            solution.setText("");
            result.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            solution.setText(result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err"))
        {
            result.setText(finalResult);
        }
    }

    String getResult(String data){
        String finalResult;
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        try{
            Scriptable scriptable = context.initStandardObjects();
            finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
//            if(finalResult.endsWith(".0"))
//            {
//                finalResult = finalResult.replace(".0","");
//            }
//            return finalResult;
        }catch (Exception e){
            finalResult = "0";
//            return finalResult;
        }
        return finalResult;
    }
}