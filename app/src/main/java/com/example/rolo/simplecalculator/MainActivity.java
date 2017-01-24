package com.example.rolo.simplecalculator;

import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        updateCurrentOP();
        bindRatioGroupListener();
    }
    private RadioGroup radioGroup;
    private void updateCurrentOP(){
        RadioButton radioButtonCurrent;
        radioButtonCurrent = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
        switch (radioButtonCurrent.getText().toString()){
            case "+":
                currentOP = OP.ADD;
                break;
            case "-":
                currentOP = OP.MINUS;
                break;
            case "*":
                currentOP = OP.MULTIPLE;
                break;
            case "/":
                currentOP = OP.DEVIDE;
                break;
        }
    }
    private OP currentOP;
    private void bindRatioGroupListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateCurrentOP();
                String output = ((RadioButton)findViewById(checkedId)).getText().toString();
                Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private enum OP{ADD,MINUS,MULTIPLE,DEVIDE};
    public void onClickCalculate(View v){
        EditText num1_text = (EditText)findViewById(R.id.editText_num1);
        EditText num2_text = (EditText)findViewById(R.id.editText_num2);
        double num1 = 0.0,num2 = 0.0;
        try {
            num1 = Double.parseDouble(num1_text.getText().toString());
            num2 = Double.parseDouble(num2_text.getText().toString());
        }catch(Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Please input legal Decimal Number input").setCancelable(true);
            AlertDialog alert = builder.create();
            alert.show();
        }
        double result = num1 + num2;
        TextView tv_result = (TextView) findViewById(R.id.textView_result);
        tv_result.setText(String.valueOf(result));
    }
}
