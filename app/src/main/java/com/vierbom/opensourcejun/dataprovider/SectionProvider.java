package com.vierbom.opensourcejun.dataprovider;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.vierbom.opensourcejun.model.Section;

import java.util.ArrayList;
import java.util.List;


public class SectionProvider extends BasicCommonDataProvider {

    private static SectionProvider instance;

    public static SectionProvider getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (SectionProvider.class) {
            instance = new SectionProvider();
        }
        return instance;
    }

    public static void addToSectionTable(List<Section> sections) {
        if (sections == null)
            return;
        getInstance().db().save(sections);
    }

    public static List<Section> getSections(String value){
        QueryBuilder queryBuilder = new QueryBuilder(Section.class);
        queryBuilder.whereEquals("chapter",value);
        ArrayList<Section> sections=getInstance().db().query(queryBuilder);
        return sections;
    }




}
