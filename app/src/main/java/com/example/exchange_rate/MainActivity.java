package com.example.exchange_rate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //歌词更新进度

                    break;
                default:
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final rate r = new rate();
        final TextView hellow = findViewById(R.id.hello);         //首先发送http请求要在线程里发送   其次 https://blog.csdn.net/nishigesb123/article/details/91396144
        final Handler handler2 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                String s=(String)msg.obj;
                String str="";
                list l=new list();
                List<Currency> a=l.getJsonList(s);
                for(int i=0;i<a.size();i++){
                    str=str+a.get(i).toString()+"\n";
                }
                hellow.setText(str);
            }
        };
        final Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                EditText e=findViewById(R.id.text);
                int progress = 0;
                    Message msg = new Message();
                    msg.obj=r.getRequest1();
                    msg.arg1=progress;
                    handler2.sendMessage(msg);
            }
        };
        Thread workThread = new Thread(null, runnable2, "runnable2");
        workThread.start();


    }
}