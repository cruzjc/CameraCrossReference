/***
 * basically a copy of "TextGraphic" from the "textdetector" package
 * it's retyped here so i can learn what it is doing
 */

package com.google.mlkit.vision.demo.java.CameraCrossReference;

import static java.lang.Math.max;
import static java.lang.Math.min;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import com.google.mlkit.vision.demo.GraphicOverlay;
import com.google.mlkit.vision.demo.GraphicOverlay.Graphic;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.Text.Element;
import com.google.mlkit.vision.text.Text.Line;
import com.google.mlkit.vision.text.Text.TextBlock;
import java.util.Arrays;


public class CrossReferencedGraphic extends Graphic{
    private static final String TAG="CrossReferencedGraphic";

    private static final int TEXT_COLOR=Color.BLACK;
    private static final int MARKER_COLOR=Color.WHITE;
    private static final float TEXT_SIZE=54.0f;
    private static final float STROKE_WIDTH=4.0f;

    private final Paint rectPaint;
    private final Paint textPaint;
    private final Paint labelPaint;
    private String text="";

    CrossReferencedGraphic(GraphicOverlay overlay, String text){
        super(overlay);

        this.text=text;

        rectPaint=new Paint();
        rectPaint.setColor(MARKER_COLOR);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(STROKE_WIDTH);

        textPaint=new Paint();
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTextSize(TEXT_SIZE);

        labelPaint=new Paint();
        labelPaint.setColor(MARKER_COLOR);
        labelPaint.setStyle(Paint.Style.FILL);

        postInvalidate();
    }


    @Override
    public void draw(Canvas canvas){
                RectF rect=new RectF(0,canvas.getHeight()*.05f,canvas.getWidth(),canvas.getHeight()*.1f);

                float x0=translateX(rect.left);
                float x1=translateX(rect.right);
                rect.left=min(x0,x1);
                rect.right=max(x0,x1);
                rect.top=translateY(rect.top);
                rect.bottom=translateY(rect.bottom);
                canvas.drawRect(rect,rectPaint);

                float lineHeight=TEXT_SIZE+2*STROKE_WIDTH;
                float textWidth=textPaint.measureText(text);
                canvas.drawRect(
                        rect.left-STROKE_WIDTH,
                        rect.top-lineHeight,
                        rect.left+textWidth+2*STROKE_WIDTH,
                        rect.top,
                        labelPaint);
                canvas.drawText(text,rect.left,rect.top-STROKE_WIDTH,textPaint);



    }

}
