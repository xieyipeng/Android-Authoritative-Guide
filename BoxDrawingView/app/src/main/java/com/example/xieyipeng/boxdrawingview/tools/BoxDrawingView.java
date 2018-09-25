package com.example.xieyipeng.boxdrawingview.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieyipeng on 2018/9/25.
 */

public class BoxDrawingView extends View {

    private static final String TAG="BoxDrawingView";
    private Box currentBox;
    private List<Box> boxList=new ArrayList<>();

    private Paint boxPaint;
    private Paint backgroundPaint;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current =new PointF(event.getX(),event.getY());
        String action="";
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action="ACTION_DOWN";

                currentBox=new Box(current);
                boxList.add(currentBox);

                break;
            case MotionEvent.ACTION_MOVE:
                action="ACTION_MOVE";

                if (currentBox!=null){
                    currentBox.setCurrrnt(current);
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
                action="ACTION_UP";
                currentBox=null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action="ACTION_CANCEL";
                currentBox=null;
                break;
        }
        Log.e(TAG, "onTouchEvent: action "+action+ " at x= "+current.x+", y= "+current.y);
        return true;
    }

    //create
    public BoxDrawingView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(backgroundPaint);
        for (int i = 0; i < boxList.size(); i++) {
            float left=Math.min(boxList.get(i).getOrigin().x,boxList.get(i).getCurrrnt().x);
            float right=Math.max(boxList.get(i).getOrigin().x,boxList.get(i).getCurrrnt().x);
            float top=Math.min(boxList.get(i).getOrigin().y,boxList.get(i).getCurrrnt().y);
            float bottom=Math.max(boxList.get(i).getOrigin().y,boxList.get(i).getCurrrnt().y);
            canvas.drawRect(left,top,right,bottom,boxPaint);
        }
    }

    //inflating
    public BoxDrawingView(Context context, AttributeSet attributes){
        super(context,attributes);

        boxPaint= new Paint();
        boxPaint.setColor(0x22ff0000);

        backgroundPaint=new Paint();
        backgroundPaint.setColor(0xfff8efe0);

    }
}

