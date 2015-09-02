package com.vierbom.opensourcejun.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

@Table("Section")
public class Section {
    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
    public int id;
    private String chapter;
    private String section;

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getChapter() {
        return chapter;
    }

    public String getSection() {
        return section;
    }
}
