/***
 * displays csv info
 * uses "InferenceInfoGraphic as a template/reference
 */
package com.google.mlkit.vision.demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;

public class CsvInfoGraphic extends GraphicOverlay.Graphic {
    private static final int TEXT_COLOR=Color.WHITE;
    private static final float TEXT_SIZE=60.f;

    private final Paint textPaint;
    private final GraphicOverlay overlay;
    private final String csvFileName;

    public CsvInfoGraphic(
            GraphicOverlay overlay,
            String csvFileName
    ){
        super(overlay);
        this.overlay=overlay;
        this.csvFileName=csvFileName;
        textPaint=new Paint();
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTextSize(TEXT_SIZE);
        postInvalidate();
    }

    @Override
    public synchronized void draw(Canvas canvas){
        float x=TEXT_SIZE*0.5f;
        float y=TEXT_SIZE*1.5F;

        canvas.drawText(
                "Filename: "+this.csvFileName,
                x,
                y,
                textPaint
        );

    }
}
