package com.example.xieyipeng.criminallintent.tools;

import java.util.Date;
import java.util.UUID;

/**
 * Created by xieyipeng on 2018/9/18.
 */

public class Crime {
    private UUID uuid;
    private String title;
    private Date date;
    private boolean solved;

    public Crime(){
        uuid=UUID.randomUUID();
        date=new Date();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
