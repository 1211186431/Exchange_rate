package com.example.exchange_rate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CActivity extends AppCompatActivity {
    private List<String> slist = new ArrayList<String>();
    private List<Currency> alllist = new ArrayList<Currency>();
    String from="无";
    String to="无";
    String rul="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final rate r = new rate();
        slist.add("无");  //需要给它设置初始值不然选了没用  可能是加载时线性加载的
        alllist.add(new Currency("无","无"));
        final EditText hellow = findViewById(R.id.hello);         //首先发送http请求要在线程里发送   其次 https://blog.csdn.net/nishigesb123/article/details/91396144
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.arg1) {
                    case 0:
                        String s=(String)msg.obj;
                        list l=new list();
                        List<Currency> a=l.getJsonList(s);
                        for(int i=0;i<a.size();i++){
                            slist.add(a.get(i).getName());
                            alllist.add(a.get(i));
                        }
                        break;
                    case 1:
                        String s2=(String)msg.obj;
                        list l2=new list();
                        List<currency2> a2=l2.getJsonList2(s2);
                        TextView hello=findViewById(R.id.hello);
                        String h=hello.getText().toString();
                        if(!a2.isEmpty()){
                            for(int i=0;i<a2.size();i++){
                                rul=ji_suan(h,a2.get(i))+rul+a2.get(i).toString()+"\n";
                            }
                            TextView textView=findViewById(R.id.result1);
                            textView.setText(rul);
                            rul="";
                        }
                        else {
                            TextView textView=findViewById(R.id.result1);
                            textView.setText("查询失败");
                        }
                        break;
                    default:
                        break;
                }

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
                mHandler.sendMessage(msg);
            }
        };
        Thread workThread = new Thread(null, runnable2, "runnable2");
        workThread.start();

        Spinner spinner2 = (Spinner) findViewById(R.id.spring1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, slist);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter1);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                from=NameToCode(parent.getSelectedItem().toString());
                Log.v("Tag",from);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callbac
            }
        });
        Spinner spinner1 = (Spinner) findViewById(R.id.spring2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, slist);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                to=NameToCode(parent.getSelectedItem().toString());
                Log.v("Tag",to);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callbac
            }
        });
        Button button=findViewById(R.id.ji_suan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView result=findViewById(R.id.result1);
                if(from.equals("无")&&to.equals("无")){
                    result.setText("计算错误");
                }
                else{
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            EditText e=findViewById(R.id.text);
                            int progress = 1;
                            Message msg2 = new Message();
                            Log.v("myTag",from+" "+to);
                            msg2.obj= rate.getRequest3(from,to);
                            msg2.arg1=progress;
                            mHandler.sendMessage(msg2);
                        }
                    };
                    Thread workThread3 = new Thread(null, runnable, "runnable");
                    workThread3.start();
                }
            }
        });

    }

    public String NameToCode(String name){
        String code="无";
        for(int i=0;i<alllist.size();i++){
            if(alllist.get(i).getName().equals(name)){
                code=alllist.get(i).getCode();
            }
        }
        return code;
    }
    public String ji_suan(String h,currency2 c){
        String Str="";
        double after=0;
        double before;
        Boolean isTrue=true;
        for(int i=0;i<h.length();i++){
            if(h.charAt(i)>='0'&&h.charAt(i)<='9'){

            }
            else{
                isTrue=false;
                break;
            }
        }
        if(isTrue){
            before=Double.parseDouble(h);
        }

        else{
            before=1;
        }

        String result=c.getResult();
        after=before*Double.parseDouble(result);
        Str=before+""+c.getF_name()+" = "+after+c.getT_name()+"\n";
        return Str;
    }


}