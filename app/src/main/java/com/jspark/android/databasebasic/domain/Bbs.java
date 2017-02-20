package com.jspark.android.databasebasic.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by jsPark on 2017. 2. 14..
 */

@DatabaseTable(tableName = "bbs")
public class Bbs {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String content;

    @DatabaseField
    private Date currentDate;

    public String getContent() {
        return content;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // 없으면 OrmLite 작동 안함
    public Bbs() {

    }

    public Bbs(String title, String content, Date currentDate) {
        this.title = title;
        this.content = content;
        this.currentDate = currentDate;
    }
}
