package com.vierbom.opensourcejun.dataprovider;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.vierbom.opensourcejun.model.Name;

import java.util.ArrayList;
import java.util.List;


public class NameProvider extends BasicCommonDataProvider {

    private static NameProvider instance;

    public static NameProvider getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (NameProvider.class) {
            instance = new NameProvider();
        }
        return instance;
    }

    public static void addToNameTable(List<Name> names) {
        if (names == null)
            return;
        getInstance().db().save(names);
    }

    public static List<Name> getNames(String value){
        QueryBuilder queryBuilder = new QueryBuilder(Name.class);
        queryBuilder.whereEquals("section",value);
        ArrayList<Name> sections=getInstance().db().query(queryBuilder);
        return sections;
    }



}
