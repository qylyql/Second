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
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity";
    //TextView dollar2,euro2,won2;
    TextView dollarText,euroText,wonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent=getIntent();
        float dollarrate=intent.getFloatExtra("dollar_rate_key",0.0f);
        float eurorate=intent.getFloatExtra("euro_rate_key",0.0f);
        float wonrate=intent.getFloatExtra("won_rate_key",0.0f);
       // Log.i(TAG, "onCreate: dollar2="+dollar2);
       // Log.i(TAG, "onCreate: euro2="+dollar2);
       // Log.i(TAG, "onCreate: won2="+dollar2);

        dollarText=findViewById(R.id.new_dollar);
        dollarText.setText(String.valueOf(dollarrate));
        euroText=findViewById(R.id.new_euro);
        euroText.setText(String.valueOf(eurorate));
        wonText=findViewById(R.id.new_won);
        wonText.setText(String.valueOf(wonrate));



    }

    public void save(View btn){
        Log.i(TAG, "save: ");
        //获取新输入的值
        //float newDollar=Float.parseFloat(dollarText.getText().toString());
        //float newEuro=Float.parseFloat(euroText.getText().toString());
        //float newWon=Float.parseFloat(wonText.getText().toString());


        //保存数据到sp
        //saveToSP(newDollar,newEuro,newWon);
        Intent intent=getIntent();

        EditText dollar_key2=findViewById(R.id.new_dollar);
        float dollarRate=Float.parseFloat(dollar_key2.getText().toString());
        EditText euro_key2=findViewById(R.id.new_euro);
        float euroRate=Float.parseFloat(euro_key2.getText().toString());
        EditText won_key2=findViewById(R.id.new_won);
        float wonRate=Float.parseFloat(won_key2.getText().toString());
        intent.putExtra("dollar_key2", dollarRate);//存入到intent中。
        intent.putExtra("euro_key2", euroRate);
        intent.putExtra("won_key2", wonRate);

        SharedPreferences sp=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putFloat("dollar_rate", dollarRate);
        editor.putFloat("euro_rate", euroRate);
        editor.putFloat("won_rate", wonRate);
        editor.apply();
        Log.i(TAG, "save: dollarRate="+dollarRate);
        setResult(3,intent);
        finish();



       //Bundle bdl = new Bundle();
       /*bdl.putFloat("dollar_key3",newDollar);
       bdl.putFloat("euro_key3",newEuro);
        bdl.putFloat("won_key3",newWon);
        first.putExtras(bdl);
        setResult(5,first);
*/


        //关闭当前窗口
       // finish();

    }
/*
    private void saveToSP(float newDollar, float newEuro, float newWon) {
        SharedPreferences sharedPreferences=getSharedPreferences("myrate",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat("dollar_rate", newDollar);
        editor.putFloat("euro_rate", newEuro);
        editor.putFloat("won_rate", newWon);
        editor.apply();
    }
    
 */
}