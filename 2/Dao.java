package com.example.eight;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @类名 : Dao
 * @作者 : SUNX
 * @描述 ：数据库的增删改查
 */
public class Dao {

    private static final String TAG = "Dao" ;
    private final DatabaseHelper mhelper;

    public Dao(Context context){
        //创建数据库
        mhelper = new DatabaseHelper(context);
    }

    public void insert(int id,int GradeUnit,int Unit,String En,String Ch){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        //添加数据
        //1.写好sql语句  insert into table_name (id,GradeUnit,Unit,English,Chinese)
        //2.将sql语句交给SQLiteDatabase.execSQL()执行
        //3.将SQLiteDatabase资源关闭
        //String sql = "insert into "+ Database.TABLE_NAME + " (id,GradeUnit,Unit,English,Chinese) values (?,?,?,?,?)";
        //db.execSQL(sql,new Object[]{id,GradeUnit,Unit,En,Ch});

        //API插入数据
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("GradeUnit",GradeUnit);
        values.put("Unit",Unit);
        values.put("English",En);
        values.put("Chinese",Ch);
        db.insert("library",null,values);
        db.close();
    }

    public void delete(int id,int GradeUnit,int Unit,String En,String Ch){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        //删除数据
        //1.写好sql语句 delete from table_name where conditions
        //2.将sql语句交给SQLiteDatabase.execSQL()执行
        //3.将SQLiteDatabase资源关闭
        String sql = "delete from " + "library" +" where English = '" +En + "'";
        db.execSQL(sql);
        db.close();
    }

    public void delete(int GradeUnit,int Unit,String English,String Chinese){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        //删除数据
        //1.写好sql语句 delete from table_name where conditions
        //2.将sql语句交给SQLiteDatabase.execSQL()执行
        //3.将SQLiteDatabase资源关闭
        String sql = "delete from " +"library" +" where GradeUnit =" + GradeUnit;
        db.execSQL(sql);
        db.close();
    }


    public void update(int id,int GradeUnit,int Unit,String En,String Ch){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        //更改数据
        //1.写好sql语句 update table_name set ***  where conditions
        //2.将sql语句交给SQLiteDatabase.execSQL()执行
        //3.将SQLiteDatabase资源关闭
        //String sql = "update " + Database.TABLE_NAME + " set Chinese = '"+ Ch+"' where English = '"+En+"'" ;
        //db.execSQL(sql);

        /**
         * SQLiteDatabase.update()
         * @param table : 表名
         * @param values : 修改的值  类型为 ContentValues 通过put方法加入
         * @param whereClause 修改的条件key  "English = ?"
         * @param whereArgs  修改该条件的条件值value String[] args = "ear"
         * whereClause + whereArgs 等同于 where English = 'ear'
         */
        ContentValues values = new ContentValues();
        values.put("Chinese",Ch);
        String condition ="English = ?";
        String[] args = {String.valueOf("ear")};
        db.update("library",values,condition,args);

        db.close();
    }

    public void query(int GradeUnit){
        SQLiteDatabase db = mhelper.getReadableDatabase();
        //查询数据
        //1.写好sql语句 select * from table_name where conditions
        //2.将sql语句交给SQLiteDatabase.execSQL()执行
        //3.将SQLiteDatabase资源关闭
        /*String sql = "select * from " + Database.TABLE_NAME + " where GradeUnit ="+ GradeUnit;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex("English");
            String name = cursor.getString(index);
            Log.d( TAG,"English ==" + name);
        }
        cursor.close();
        */

        /**
         * @param String[] columns 要提取的关键字键值key
         * @param String selection 查询条件的键值key
         * @param String[] selectionArgs 查询条件的value值
         * 相当于select String[] columns from table_name where String selection = "selectinoArgs"
         */
        String[] columns = new String[]{"English"};
        String selection = "GradeUnit = ?";
        String[] selectionArgs =  {String.valueOf(33)};
        db.query("library",columns,selection,selectionArgs,null,null,null);

        db.close();
    }
}

