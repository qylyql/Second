package com.swufestu.second;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";
    EditText dollarText,euroText,wonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent=getIntent();
        float dollar2=intent.getFloatExtra("dollar_rate_key",0.0f);
        float euro2=intent.getFloatExtra("euro_rate_key",0.0f);
        float won2=intent.getFloatExtra("won_rate_key",0.0f);
        Log.i(TAG, "onCreate: dollar2="+dollar2);
        Log.i(TAG, "onCreate: euro2="+dollar2);
        Log.i(TAG, "onCreate: won2="+dollar2);

        dollarText=findViewById(R.id.new_dollar);
        euroText=findViewById(R.id.new_euro);
        wonText=findViewById(R.id.new_won);

        dollarText.setText(String.valueOf(dollar2));
        euroText.setText(String.valueOf(euro2));
        wonText.setText(String.valueOf(won2));

    }

    public void save(View btn){
        Log.i(TAG, "save: ");
        //获取新输入的值
        float newDollar=Float.parseFloat(dollarText.getText().toString());
        float newEuro=Float.parseFloat(euroText.getText().toString());
        float newWon=Float.parseFloat(wonText.getText().toString());


        //保存数据到sp
        saveToSP(newDollar,newEuro,newWon);

        Intent first=getIntent();
       Bundle bdl = new Bundle();
       bdl.putFloat("dollar_key3",newDollar);
       bdl.putFloat("euro_key3",newEuro);
        bdl.putFloat("won_key3",newWon);
        first.putExtras(bdl);
        setResult(5,first);



        //关闭当前窗口
        finish();

    }

    private void saveToSP(float newDollar, float newEuro, float newWon) {
        SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat("dollar_rate", newDollar);
        editor.putFloat("euro_rate", newEuro);
        editor.putFloat("won_rate", newWon);
        editor.apply();
    }
}