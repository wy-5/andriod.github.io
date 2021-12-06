package com.example.a18365;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

 public class Sea_deluser_Activity extends Activity {
    public ListView user_list;
    private List<userInfo> list;
    private SQLiteDatabase sqLiteDatabase;
    //假设数据库用户不超过10个
    private String[] user_mes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.user_search_delete);
        user_list = findViewById(R.id.mes);
        user_database users = new user_database(Sea_deluser_Activity.this);
        sqLiteDatabase = users.getReadableDatabase();
        //获取从数据库查询到的数据
        list = users.querydata(sqLiteDatabase);
        //把获取到的信息添加到用户名数组中
        user_mes = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            user_mes[i] = "ID号："+list.get(i).getId() + " " +"用户名："+list.get(i).getUsername() + " " +"密码："+
                    list.get(i).getPaswd() + " " + "年龄："+list.get(i).getAge() + " " +"性别："+
                    list.get(i).getSex();
        }
            //把用户名显示在ListView上
            final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (Sea_deluser_Activity.this,android.R.layout.simple_list_item_1,user_mes);
            user_list.setAdapter(adapter);
            //为listview每个元素添加单击事件
            user_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final int id = list.get(i).getId();
                    //弹出一个对话框
                    new AlertDialog.Builder(Sea_deluser_Activity.this).setTitle("系统提示")
                            //设置显示的内容
                            .setMessage("确定删除该条数据吗!")
                            //添加确定按钮
                            .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which) {
                                    //删除数据操作，首先获取到id
                                    user_database user_database = new user_database(Sea_deluser_Activity.this);
                                    SQLiteDatabase sqLiteDatabase = user_database.getReadableDatabase();
                                    user_database.delete(sqLiteDatabase,id);
                                    refresh();
                                    Toast.makeText(Sea_deluser_Activity.this,"删除成功", Toast.LENGTH_SHORT).show();
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener(){//添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();//在按键响应事件中显示此对话框
                }
            });
        }
     //刷新页面方法
    private void refresh() {
        finish();
        Intent intent = new Intent(Sea_deluser_Activity.this, Sea_deluser_Activity.class);
        startActivity(intent);
    }
 }

