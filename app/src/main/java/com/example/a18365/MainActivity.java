package com.example.a18365;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


    public class MainActivity extends Activity implements View.OnClickListener {
        public user_database user;
        public SQLiteDatabase sqL_read;
        private Button search_deal_btn,insert_btn,update_btn;

        @Override
        protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            user_database user = new user_database(MainActivity.this);
            //获取一个可读的数据库
            sqL_read = user.getReadableDatabase();
            init();
        }

        //组件初始化方法
        public void init() {
            search_deal_btn = (Button)findViewById(R.id.search_delete);
            insert_btn = (Button) findViewById(R.id.add);
            update_btn = (Button) findViewById(R.id.update);
            //添加监听
            search_deal_btn.setOnClickListener(this);
            insert_btn.setOnClickListener(this);
            update_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.search_delete:
                    Intent intent1 = new Intent(MainActivity.this, Sea_deluser_Activity.class);
                    startActivity(intent1);
                    break;
                case R.id.add:
                    Intent intent2 = new Intent(MainActivity.this, Insertuser_Activity.class);
                    startActivity(intent2);
                    break;
                case R.id.update:
                    Intent intent3 = new Intent(MainActivity.this, Updareuser_Activity.class);
                    startActivity(intent3);
                    break;
                default:
                    break;
            }
        }
    }

