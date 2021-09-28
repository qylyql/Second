package com.swufestu.second;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FirstActivity extends AppCompatActivity implements  Runnable{
    private static final String TAG = "FirstActivity";
   // float dollar_rate = 0.35f;
   // float euro_rate = 0.28f;
    //float won_rate = 501f;
    float dollarRate = 0.35f;
    float euroRate = 0.28f;
    float wonRate = 501f;
    Handler handler;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        result=findViewById(R.id.result);

        //获取文件中保存的数据
        SharedPreferences sharedPreferences=getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        //PreferenceManager.getDefaultSharedPreferences(this);
        dollarRate=sharedPreferences.getFloat("dollar_rate",0.35f);
        euroRate=sharedPreferences.getFloat("euro_rate",0.28f);
        wonRate=sharedPreferences.getFloat("won_rate",501f);
        Log.i(TAG, "onCreat: dollarRate="+dollarRate);
        Log.i(TAG, "onCreat: euroRate= "+euroRate);
        Log.i(TAG, "onCreat: wonRate=" + wonRate);

        handler=new Handler(Looper.myLooper()){
            public void handleMessage(Message msg){
                Log.i(TAG, "handleMessage: 收到消息");
                if(msg.what==6){
                    String str=(String)msg.obj;
                    Log.i(TAG, "handleMessage: str="+str);
                    result.setText(str);
                }
                super.handleMessage(msg);
            }
        };

        Thread thread = new Thread(this);
        thread.start();

    }

    public void click(View btn) {

        Log.i(TAG, "click: ");
        EditText rmb = findViewById(R.id.input_rmb);
        //TextView result = findViewById(R.id.result);
        String inp = rmb.getText().toString();
        Log.i(TAG, "click: click:inp=" + inp);
        if (inp!=null&&inp.length() > 0) {
            float num = Float.parseFloat(inp);
            float r = 0;
            if (btn.getId() == R.id.btn_dollar) {
                r = num * dollarRate;
            } else if (btn.getId() == R.id.btn_euro) {
                r = num * euroRate;
            } else {
                r = num * wonRate;
            }
            Log.i(TAG, "click: r=" + r);
            result.setText(String.valueOf(r));
        } else {
            Toast.makeText(this, "请输入金额后再转换", Toast.LENGTH_SHORT).show();
            result.setText(R.string.hello1);
        }
    }

    public void openConfig(View btn){
        Log.i(TAG, "openConfig: ");
        config();
    }

    private void config() {
        Intent config=new Intent(this,ThirdActivity.class);
        config.putExtra("dollar_rate_key",dollarRate);
        config.putExtra("euro_rate_key",euroRate);
        config.putExtra("won_rate_key",wonRate);
        Log.i(TAG, "openConfig: dollar_rate="+dollarRate);
        Log.i(TAG, "openConfig: euro_rate="+euroRate);
        Log.i(TAG, "openConfig: won_rate="+wonRate);

        //startActivity(config);
        startActivityForResult(config,1);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==1&&resultCode==3){
            dollarRate=data.getFloatExtra("dollar_key2",0.1f);
            euroRate=data.getFloatExtra("euro_key2",0.1f);
            wonRate=data.getFloatExtra("won_key2",0.1f);

            Log.i(TAG, "onActivityResult: dollar_rate="+dollarRate);
            Log.i(TAG, "onActivityResult: euro_rate="+euroRate);
            Log.i(TAG, "onActivityResult: won_rate="+wonRate);


        }else if(requestCode==1&&resultCode==5)
             {
            Bundle bundle=data.getExtras();
                 dollarRate=bundle.getFloat("dollar_key3",1);
                 euroRate=bundle.getFloat("euro_key3",1);
                 wonRate=bundle.getFloat("won_key3",1);

                 Log.i(TAG, "onActivityResult: dollar_rate3="+dollarRate);
                 Log.i(TAG, "onActivityResult: euro_rate3= "+euroRate);
                 Log.i(TAG, "onActivityResult: won_rate3="+wonRate);

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

    @Override
    public void run() {
        Log.i(TAG, "run:run...... ");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        URL url=null;
        try {
            Log.i(TAG, "run: fw url");
            url =new URL("http://www.swufe.edu.cn/info/1002/18045.htm");
            HttpURLConnection http=(HttpURLConnection)url.openConnection();
            InputStream in =http.getInputStream();

            String html= inputStream2String(in);
            Log.i(TAG, "run: html="+html);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        //发送消息
        Message msg=handler.obtainMessage(6);
        //msg.what=6;
        msg.obj="Hello from run";
        handler.sendMessage(msg);
        Log.i(TAG, "run: 消息已发送");
    }

    private String inputStream2String(InputStream inputStream) throws IOException {
        final int bufferSize=1024;
        final char[] buffer =new char[bufferSize];
        final StringBuilder out=new StringBuilder();
        String charsetName;
        Reader in =new InputStreamReader(inputStream,charsetName="utf-8");
        while (true){
            int rsz=in.read(buffer,0,buffer.length);
            if(rsz<0)
                break;
            out.append(buffer,0,rsz);
        }
          return  out.toString();

    }
}



