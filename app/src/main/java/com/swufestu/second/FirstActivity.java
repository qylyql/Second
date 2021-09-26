package com.swufestu.second;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {


    private static final String TAG = "FirstActivity";

    float dollar_rate = 0.35f;
    float euro_rate = 0.28f;
    float won_rate = 501f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    public void click(View btn) {

        Log.i(TAG, "click: ");
        EditText rmb = findViewById(R.id.input_rmb);
        TextView result = findViewById(R.id.result);
        String inp = rmb.getText().toString();
        Log.i(TAG, "click: click:inp=" + inp);
        if (inp!=null&&inp.length() > 0) {
            float num = Float.parseFloat(inp);
            float r = 0;
            if (btn.getId() == R.id.btn_dollar) {
                r = num * dollar_rate;
            } else if (btn.getId() == R.id.btn_euro) {
                r = num * euro_rate;
            } else {
                r = num * won_rate;
            }
            Log.i(TAG, "click: r=" + r);
            result.setText(String.valueOf(r));
        } else {
            Toast.makeText(this, "请输入金额后再转换", Toast.LENGTH_SHORT).show();
            result.setText(R.string.hello1);
        }
    }
/*
    public void openOne(View btn) {
        Log.i(TAG, "open:1111111111 ");
        Intent config = new Intent(this, ThirdActivity.class);
        //Intent first =new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jd.com"));

        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);

        Log.i(TAG, "openOne: dollorRate="+dollorRate);
        Log.i(TAG, "openOne: euroRate="+euroRate);
        Log.i(TAG, "openOne: wonRate="+wonRate);

        startActivity(config);
        startActivityForResult(config,1000);
    }
*/


    public void openConfig(View btn){
        Log.i(TAG, "openConfig: ");
        config();
    }

    private void config() {
        Intent config=new Intent(this,ThirdActivity.class);
        config.putExtra("dollar_rate_key",dollar_rate);
        config.putExtra("euro_rate_key",euro_rate);
        config.putExtra("won_rate_key",won_rate);
        Log.i(TAG, "openConfig: dollar_rate="+dollar_rate);
        Log.i(TAG, "openConfig: euro_rate="+euro_rate);
        Log.i(TAG, "openConfig: won_rate="+won_rate);

        //startActivity(config);
        startActivityForResult(config,1);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==1&&resultCode==3){
            dollar_rate=data.getFloatExtra("dollar_key2",0.1f);
            euro_rate=data.getFloatExtra("euro_key2",0.1f);
            won_rate=data.getFloatExtra("won_key2",0.1f);

            Log.i(TAG, "onActivityResult: dollar_rate=" +
                    ""+dollar_rate);
            Log.i(TAG, "onActivityResult: euro_rate="+euro_rate);
            Log.i(TAG, "onActivityResult: won_rate="+won_rate);


        }else if(requestCode==1&&resultCode==5){
            Bundle bundle=data.getExtras();
            dollar_rate=bundle.getFloat("dollar_key3",1);
            euro_rate=bundle.getFloat("euro_key3",1);
            won_rate=bundle.getFloat("won_key3",1);

            Log.i(TAG, "onActivityResult: dollar_rate3="+dollar_rate);
            Log.i(TAG, "onActivityResult: euro_rate3= "+euro_rate);
            Log.i(TAG, "onActivityResult: won_rate3="+won_rate);
        }
        super.onActivityResult(requestCode , resultCode,data);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
       // return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.setting){
            config();
        }
        return super.onOptionsItemSelected(item);
    }

}



