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

  public class Insertuser_Activity extends Activity {
      private EditText name_edit, paswd_edit, age_edit;
      private Spinner spinner;
      private Button save_btn;
      private String select_sex ;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          requestWindowFeature(Window.FEATURE_NO_TITLE);
          setContentView(R.layout.user_insert);
          init();
      }

      public void init() {
          name_edit = (EditText) findViewById(R.id.insert_name);
          paswd_edit = (EditText) findViewById(R.id.insert_paswd);
          spinner = (Spinner)findViewById(R.id.insert_sex);
          //为选择性别下拉列表框添加选择事件
          spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 //获取选择的值
                 select_sex = Insertuser_Activity.this.getResources().getStringArray(R.array.sex)[i];
              }

              @Override
              public void onNothingSelected(AdapterView<?> adapterView) {
              }
          });
          age_edit = (EditText) findViewById(R.id.insert_age);
          save_btn = (Button) findViewById(R.id.save_usermes);
          save_btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view){
                  //获取用户输人的用户名、密码、年纪
                  String name_str = name_edit.getText().toString();
                  String paswd_str = paswd_edit.getText().toString();
                  int age = Integer.parseInt(age_edit.getText().toString());
                  //调用数据库操作类的插入方法
                  user_database us_db = new user_database(Insertuser_Activity.this);
                  SQLiteDatabase sqLiteDatabase = us_db.getWritableDatabase();
                  us_db.adddata(sqLiteDatabase, name_str, paswd_str,select_sex, age);
                  Intent intent = new Intent(Insertuser_Activity.this,
                          Sea_deluser_Activity.class);
                  startActivity(intent);
              }
          });
      }
  }