package com.xyp.tiange.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xyp
 * Date: 2017/6/22
 * Time: 15:42
 */

public class DataBase {
    private static DataBase dataBase;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    private DataBase(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }
    public static DataBase getInstance(Context context){
        if(dataBase == null){
            synchronized (DataBase.class){
                if(dataBase == null){
                    dataBase = new DataBase(context.getApplicationContext());
                }
            }
        }
        return dataBase;
    }

    private void openSQLiteWrite(){
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
    }
    private void openSQLiteReader(){
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
    }
    private void closeSQLite(){
        sqLiteDatabase.close();
    }

    /**
     * 插入一个用户数据
     */
    public void insertUser(String name,int age){
        openSQLiteWrite();
        ContentValues contentValues = new ContentValues();
        //key 为表中的字段名
        contentValues.put("name",name);
        contentValues.put("age",age);
        sqLiteDatabase.replaceOrThrow(DataBaseInfo.USER_TABLE_NAME,null,contentValues);//如果contentValues为null，那么就会插入一行null或者0
        closeSQLite();
    }

    /**
     * 删除一个用户数据
     */
    public void deleteUser(int id){
        openSQLiteWrite();
        sqLiteDatabase.delete(DataBaseInfo.USER_TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
        closeSQLite();
    }
    /**
     * 清空表中的所有数据
     */
    public void deleteAll(){
        openSQLiteWrite();
        sqLiteDatabase.delete(DataBaseInfo.USER_TABLE_NAME,null,null);
        closeSQLite();
    }
    /**
     * 修改一个用户数据
     */
    public void updateUser(int id,int age){
        openSQLiteWrite();
        ContentValues contentValues = new ContentValues();
        contentValues.put("age",age);
        String[] arg = {String.valueOf(id)};
        sqLiteDatabase.update(DataBaseInfo.USER_TABLE_NAME,contentValues,"id=?",arg);
        closeSQLite();
    }

    /**
     * 获取某一个用户信息
     */
    public User getUserForId(int id){
        openSQLiteReader();
        String sql = "select name,age from "+DataBaseInfo.USER_TABLE_NAME +" where id="+id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        User user = new User();
        for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndexOrThrow("age")));
        }
        cursor.close();
        closeSQLite();
        return user;
    }
    /**
     * 获取所有用户信息
     */
    public List<User> getAllUser(){
        openSQLiteReader();
        String sql = "select * from "+DataBaseInfo.USER_TABLE_NAME;
        List<User> userList = new ArrayList<User>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User();
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndexOrThrow("age")));
            userList.add(user);
            user = null;
            cursor.moveToNext();
        }
        cursor.close();
        closeSQLite();
        return userList;
    }
}
