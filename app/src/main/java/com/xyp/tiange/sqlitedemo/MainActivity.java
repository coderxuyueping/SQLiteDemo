package com.xyp.tiange.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText idEt,nameEt,ageEt;
    private TextView infoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idEt = (EditText) findViewById(R.id.id);
        nameEt = (EditText) findViewById(R.id.name);
        ageEt = (EditText) findViewById(R.id.age);
        infoTv = (TextView) findViewById(R.id.info_tv);
    }

    public void insert(View view){
        if(TextUtils.isEmpty(ageEt.getText().toString()))
            return;
        DataBase.getInstance(this).insertUser(nameEt.getText().toString(),Integer.valueOf(ageEt.getText().toString()));
        clean();
    }

    public void update(View view){
        if(TextUtils.isEmpty(ageEt.getText().toString()) || TextUtils.isEmpty(idEt.getText().toString()))
            return;
        DataBase.getInstance(this).updateUser(Integer.valueOf(idEt.getText().toString()),Integer.valueOf(ageEt.getText().toString()));
        clean();
    }

    public void deleteForId(View view){
        if(TextUtils.isEmpty(idEt.getText().toString()))
            return;
        DataBase.getInstance(this).deleteUser(Integer.valueOf(idEt.getText().toString()));
        clean();
    }

    public void deleteAll(View view){
        DataBase.getInstance(this).deleteAll();
    }

    public void queryForId(View view){
        if(TextUtils.isEmpty(idEt.getText().toString()))
            return;
        User user = DataBase.getInstance(this).getUserForId(Integer.valueOf(idEt.getText().toString()));
        infoTv.setText("name:"+user.getName()+"|age:"+user.getAge()+"\n");
        clean();
    }

    public void queryAll(View view){
        List<User> list;
        list = DataBase.getInstance(this).getAllUser();
        StringBuilder stringBuilder = new StringBuilder();
        for(User user:list){
            stringBuilder.append("name:"+user.getName()+"|age:"+user.getAge()+"\n");
        }
        infoTv.setText(stringBuilder.toString());
    }

    private void clean(){
        idEt.setText("");
        nameEt.setText("");
        ageEt.setText("");
    }
}
