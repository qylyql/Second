package com.swufestu.second;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText input1= findViewById(R.id.height);
        EditText input2 = findViewById(R.id.weight);
        Button btn = findViewById(R.id.btn_count);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: BMI");

        //获取用户输入
        EditText input1= findViewById(R.id.height);
        EditText input2 = findViewById(R.id.weight);
        Double height = Double.parseDouble(input1.getText().toString());
        Double weight = Double.parseDouble(input1.getText().toString());
        Double bmi = weight / (height * height);

        TextView resault = (TextView)findViewById(R.id.txt);
        if (bmi < 18.5) {
            resault.setText("BMI" + bmi.toString() + ",您的体重偏轻");
        } else if (bmi <= 24.9) {
            resault.setText("BMI" + bmi.toString() + ",您的体重正常");
        } else if (bmi <= 29.9) {
            resault.setText("BMI" + bmi.toString() + ",您的体重偏重");
        } else if (bmi <= 34.9) {
            resault.setText("BMI" + bmi.toString() + ",您的体重肥胖!!!");
        } else if (bmi <= 39.9) {
            resault.setText("BMI" + bmi.toString() + ",您的体重过于肥胖!!!!");
        } else {
            resault.setText("BMI" + bmi.toString() + ",您的体重严重肥胖!!!!!!!");
        }
    }
}

