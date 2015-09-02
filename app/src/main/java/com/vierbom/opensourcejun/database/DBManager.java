package com.vierbom.opensourcejun.database;

import android.database.sqlite.SQLiteDatabase;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.impl.SQLiteHelper;
import com.vierbom.opensourcejun.ApplicationManager;

/**
 * 数据库管理器
 * <p/>
 * Created by xiangyang550 on 15/8/4.
 */
public class DBManager implements SQLiteHelper.OnUpdateListener {

    private static DBManager manager;

    public static DBManager manager(String dbName) {
        if (isCurrentDB(dbName)) {
            return manager;
        }
        synchronized (DBManager.class) {
            if (!(isCurrentDB(dbName))) {
                manager = new DBManager(dbName);
            }
        }
        return manager;
    }

    private static boolean isCurrentDB(String dbName) {
        return manager != null && dbName.equals(manager.dbName);
    }


    private DataBase db;

    private DataBaseConfig config;
    private String dbName;

    public DBManager(String dbName) {
        config = new DataBaseConfig(ApplicationManager.getApplicationContext(), dbName, 1,
                this);
        this.dbName = dbName;
    }


    public synchronized DataBase db() {
        if (db == null) {
            db = LiteOrm.newInstance(config);
        }
        return db;
    }

    @Override
    public void onUpdate(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //对于每张表进行数据库升级操作
    }
}
