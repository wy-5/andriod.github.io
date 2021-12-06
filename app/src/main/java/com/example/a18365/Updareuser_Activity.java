package com.example.a18365;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Updareuser_Activity extends Activity {
    private EditText id_edit,name_edit, paswd_edit, age_edit;
    private Spinner spinner;
    private Button save_btn;
    private String select_sex ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.user_updare);
        init();
    }

    public void init() {
        id_edit = (EditText) findViewById(R.id.update_id);
        name_edit = (EditText) findViewById(R.id.update_name);
        paswd_edit = (EditText) findViewById(R.id.update_paswd);
        spinner = (Spinner)findViewById(R.id.update_sex);
        //为选择性别下拉列表框添加选择事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //获取选择的值
                select_sex = Updareuser_Activity.this.getResources().getStringArray(R.array.sex)[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        age_edit = (EditText) findViewById(R.id.update_age);
        save_btn = (Button) findViewById(R.id.saveupdata_usermes);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //获取用户输人修改的ID、用户名、密码、年纪
                int id_str = Integer.parseInt(id_edit.getText().toString());
                String name_str = name_edit.getText().toString();
                String paswd_str = paswd_edit.getText().toString();
                int age = Integer.parseInt(age_edit.getText().toString());
                //调用数据库操作类的插入方法
                user_database us_db = new user_database(Updareuser_Activity.this);
                SQLiteDatabase sqLiteDatabase = us_db.getWritableDatabase();
                us_db.update(sqLiteDatabase,id_str,name_str,paswd_str,select_sex, age);
                Intent intent = new Intent(Updareuser_Activity.this,
                        Sea_deluser_Activity.class);
                startActivity(intent);
            }
        });
    }
}