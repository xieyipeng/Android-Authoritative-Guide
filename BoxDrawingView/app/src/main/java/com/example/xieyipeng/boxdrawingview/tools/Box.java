package com.example.xieyipeng.boxdrawingview.tools;

import android.graphics.PointF;

/**
 * Created by xieyipeng on 2018/9/25.
 */

public class Box {
    private PointF origin;
    private PointF currrnt;
    public Box(PointF origin){
        this.origin=origin;
        this.currrnt=origin;
    }

    public PointF getOrigin() {
        return origin;
    }

    public void setOrigin(PointF origin) {
        this.origin = origin;
    }

    public PointF getCurrrnt() {
        return currrnt;
    }

    public void setCurrrnt(PointF currrnt) {
        this.currrnt = currrnt;
    }
}
