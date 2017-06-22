package com.xyp.tiange.sqlitedemo;

/**
 * User: xyp
 * Date: 2017/6/22
 * Time: 15:43
 */

public class DataBaseInfo {
    public static final String DB_NAME = "xyp.db";//数据库名
    public static int DB_VERSION = 1;//数据库版本号，升级加1会自动执行onUpgrade
    public static final String USER_TABLE_NAME = "user";



    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
            "(id integer primary key autoincrement, " +
            "name text, " +
            "age integer );";

}
