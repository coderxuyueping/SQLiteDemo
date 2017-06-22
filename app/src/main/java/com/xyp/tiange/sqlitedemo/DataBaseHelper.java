package com.xyp.tiange.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: xyp
 * Date: 2017/6/22
 * Time: 15:42
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DataBaseInfo.DB_NAME, null, DataBaseInfo.DB_VERSION);
    }

    /**
     * 第一次在获取SQLiteDataBase(getWritableDatabase方法)后执行，以后如果数据库版本号不大于之前的，就不执行这个方法，如果大于之前的，执行onUpgrade
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseInfo.CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
