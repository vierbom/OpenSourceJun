package com.vierbom.opensourcejun.model;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

@Table("Name")
public class Name {
    @PrimaryKey(PrimaryKey.AssignType.AUTO_INCREMENT)
    public int id;
    private String chapter;
    private String section;
    private String name;
    private String introduce;
    private String address;
    private String example;
    private String doc;
    private String pic;

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getChapter() {
        return chapter;
    }

    public String getSection() {
        return section;
    }

    public String getName() {
        return name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getAddress() {
        return address;
    }

    public String getExample() {
        return example;
    }

    public String getDoc() {
        return doc;
    }

    public String getPic() {
        return pic;
    }
}
