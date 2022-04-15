package com.example.eight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG="DatabaseHelper";
    private SQLiteDatabase sqLiteDatabase;

    /**
     *
     * @ context   上下文
     * @ name      数据库名称
     * @ factory   游标
     * @ version   版本号
     */

    public DatabaseHelper(Context context) {
        super(context, "library", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate 方法；创建时回调
        Log.d(TAG,"创建数据库");
        //创建字段   这种方法是通过写好sql语句，然后交给execSQL()方法执行，来创建；
        //sql : create table table_name(id integer,GradeUnit integer,Unit integer,English varchar, Chinese varchar)
        //Database.TABLE_NAME = "vocabulary"  是在常量文件中写好的
        String sql ="create table "+"library"+" (id integer,GradeUnit integer,Unit integer,English varchar,Chinese varchar)";
        sqLiteDatabase.execSQL(sql);
    }

    /**
     *
     * @param sqLiteDatabase  资源器
     * @param i :oldversion  旧的版本号
     * @param i1:newversion  新的版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpdate方法 更新数据库时，回调
        Log.d(TAG,"更新数据库");

        //添加字段
        //alter table table_name add UNIT integer;
        String sql;

        //判断要采用的版本，进行修改
        switch (i){
            case 1:
                //版本一 则需要添加GradeUnit 和 Unit 字段
                //注意sql语句不要写错，注意sql语法
                sql = "alter table " + "library" + " add GradeUnit integer";
                sqLiteDatabase.execSQL(sql);
                sql = "alter table " +"library"+ " add Unit integer";
                sqLiteDatabase.execSQL(sql);
                break;
            case 2:
                //版本2 添加Unit这个字段
                sql = "alter table " +"library"+ " add Unit varchar(10)";
                sqLiteDatabase.execSQL(sql);
                break;
            case 3:

                break;
        }
    }
}
