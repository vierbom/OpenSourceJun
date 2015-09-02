package com.vierbom.opensourcejun.dataprovider;

import com.litesuits.orm.db.DataBase;
import com.vierbom.opensourcejun.database.DBManager;

/**
 * Created by xiangyang550 on 15/8/4.
 */
public abstract class BasicDataProvider {


    protected DataBase db() {
        return  DBManager.manager(dBName()).db();
    }

    protected abstract String dBName();



}
